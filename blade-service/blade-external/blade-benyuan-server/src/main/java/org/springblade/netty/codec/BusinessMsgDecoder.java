package org.springblade.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springblade.common.constant.NumberConstant;
import org.springblade.netty.protocol.MsgProtocol;
import org.springblade.netty.protocol.request.ServerRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义业务解码器
 * @author lijiamin
 */
public class BusinessMsgDecoder extends ByteToMessageDecoder {

	/**
	 * 消息解码量化交易
	 * flag(1 byte)+length(NumberConstant.FOUR byte,后边内容的长度)+protocol code(NumberConstant.FOUR byte)+content
	 * length的长度包括  ：消息号 + 内容
	 * @param channelHandlerContext
	 * @param byteBuf
	 * @param list
	 * @throws Exception
	 */
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
		try {
			// 数据包完整性校对
			Integer dateLength = MsgProtocol.flagSize + MsgProtocol.lengthSize + MsgProtocol.msgCodeSize;
			int readableBytes = byteBuf.readableBytes();
			System.out.println("数据包长度 = " + readableBytes);
			if (byteBuf.readableBytes() < dateLength) {
				// 数据包长度不足
				System.out.println("decode -- 数据包长度不足");
				return;
			}
			// 返回表示 ByteBuf 当前可读取的字节数
			byte[] req = new byte[readableBytes];
			// 把 ByteBuf 里面的数据全部读取到字节数组中
			byteBuf.readBytes(req);
			// 循环粘包处理
			List<byte[]> resultByes = packageHandle(req);
			for (byte[] resultBye : resultByes) {
				// 协议码处理 百千位
				byte codeByte2 = resultBye[NumberConstant.SEVEN];
				int codeTenSix100 = conversionNumberSix(codeByte2) * 100;

				// 个十位
				byte codeByte = resultBye[NumberConstant.EIGHT];
				int codeTenSix10 = conversionNumberSix(codeByte);

				// code result
				int codeTenSix = codeTenSix100 + codeTenSix10;

				// 输出
				System.out.println("消息请求协议码 = " + codeTenSix + " ------ 消息长度 = " + readableBytes);
				if (readableBytes == NumberConstant.NINE) {
					// 无参传递
					list.add(new ServerRequest(codeTenSix, ""));
				} else {
					// 有参传递
					byte[] detailBytes = Arrays.copyOfRange(resultBye, 11, resultBye.length);
					String paramStr = new String(detailBytes);
					System.out.println("有参传递 = " + paramStr);
					list.add(new ServerRequest(codeTenSix, paramStr));
				}
			}
		} catch (Exception e) {
			// 防止因为请求码异常导致端口无了 ...test
			e.printStackTrace();
		}
	}

	/**
	 * 递归处理循环粘包
	 * @param req 消息
	 * @return
	 */
	private List<byte[]> packageHandle(byte[] req) {
		// result
		List<byte[]> bytes = new ArrayList<>();
		return stickCut(bytes, req);
	}

	/**
	 * 切包
	 * @param req
	 * @return
	 */
	private List<byte[]> stickCut(List<byte[]> bytes, byte[] req) {
		// 数据包完整性校对
		Integer dateLength = MsgProtocol.flagSize + MsgProtocol.lengthSize + MsgProtocol.msgCodeSize;
		// 非空长度判断
		if (req.length != 0 && req != null) {
			// 参数体长度
			byte codeLength = req[NumberConstant.FOUR];
			byte[] ofRange = Arrays.copyOfRange(req, 0, codeLength + NumberConstant.FOUR + NumberConstant.ONE);
			if (req.length < dateLength) {
				// 数据包长度不足
				System.out.println("decode -- 数据包长度不足");
			}
			// 标签位判断
			if (ofRange[0] != 1) {
				System.out.println("flag 错误");
			} else {
				bytes.add(ofRange);
			}
			// 毡包了 递归处理
			if (req.length != ofRange.length) {
				System.out.println("毡包了 递归处理");
				byte[] copyByte = Arrays.copyOfRange(req, ofRange.length, req.length);
				return stickCut(bytes, copyByte);
			}
		}
		return bytes;
	}

	/**
	 * 字节进制转换
	 * @param codeByte
	 * @return
	 */
	private int conversionNumberSix(byte codeByte) {
		// 有个小问题 请求码不能超过9999 先这样
		String toHexCodeStr = Integer.toHexString(codeByte);
		if (toHexCodeStr.contains("f")) {
			toHexCodeStr = toHexCodeStr.substring(toHexCodeStr.length() - 2, toHexCodeStr.length());
		}
		return new Integer(toHexCodeStr);
	}


}
