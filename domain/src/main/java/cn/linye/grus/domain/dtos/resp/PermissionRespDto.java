package cn.linye.grus.domain.dtos.resp;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by Tim on 2017/7/26.
 */
@Getter
@Setter
public class PermissionRespDto {
    private Integer permissionid;

    private String permissionname;

    private String description;

    private String url;

    private Boolean ismenu;

    private String permissioncode;

    private Integer parentpermissionid;

}
