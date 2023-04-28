package com.vito.example.grpc.common;

import com.vito.framework.dto.Command;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public class CommonCommand extends Command {

    private Long createBy;
    private Long updateBy;
}
