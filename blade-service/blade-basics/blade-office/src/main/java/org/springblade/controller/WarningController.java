/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.entity.Warning;
import org.springblade.vo.WarningVO;
import org.springblade.wrapper.WarningWrapper;
import org.springblade.service.IWarningService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author Blade
 * @since 2022-07-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/warning")
@Api(value = "", tags = "接口")
public class WarningController extends BladeController {

	private IWarningService warningService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入warning")
	public R<WarningVO> detail(Warning warning) {
		Warning detail = warningService.getOne(Condition.getQueryWrapper(warning));
		return R.data(WarningWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入warning")
	public R<IPage<WarningVO>> list(Warning warning, Query query) {
		IPage<Warning> pages = warningService.page(Condition.getPage(query), Condition.getQueryWrapper(warning));
		return R.data(WarningWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入warning")
	public R<IPage<WarningVO>> page(WarningVO warning, Query query) {
		IPage<WarningVO> pages = warningService.selectWarningPage(Condition.getPage(query), warning);
		return R.data(pages);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入warning")
	public R save(@Valid @RequestBody Warning warning) {
		return R.status(warningService.save(warning));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入warning")
	public R update(@Valid @RequestBody Warning warning) {
		return R.status(warningService.updateById(warning));
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入warning")
	public R submit(@Valid @RequestBody Warning warning) {
		return R.status(warningService.saveOrUpdate(warning));
	}

	
	/**
	 * 删除 
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(warningService.deleteLogic(Func.toLongList(ids)));
	}

	
}
