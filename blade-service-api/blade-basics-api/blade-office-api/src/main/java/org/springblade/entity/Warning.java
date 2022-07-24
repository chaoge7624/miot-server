/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Blade
 * @since 2022-07-24
 */
@Data
@TableName("blade_warning")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Warning对象", description = "Warning对象")
public class Warning extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 告警级别
     */
    @ApiModelProperty(value = "告警级别")
    private Integer level;
    /**
     * 告警类型
     */
    @ApiModelProperty(value = "告警类型")
    private Integer type;
    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String detail;
    /**
     * 恢复时间
     */
    @ApiModelProperty(value = "恢复时间")
    private LocalDateTime recoverTime;
    /**
     * 是否派发了工单，0否，1是
     */
    @ApiModelProperty(value = "是否派发了工单，0否，1是")
    private Integer isWorkOrder;
    /**
     * 关联子设备系统类型
     */
    @ApiModelProperty(value = "关联子设备系统类型")
    private Integer subsystemType;
    /**
     * 告警关联的设备id
     */
    @ApiModelProperty(value = "告警关联的设备id")
    private Long equipmentId;


}
