package org.walkerljl.boss.support.mvc.auth;

/**
 * Button
 *
 * @author lijunlin
 */
public class Button extends org.walkerljl.boss.sdk.auth.model.Button {

    private static final long serialVersionUID = 1L;

    /**
     * 获取Button主体
     *
     * @return
     */
    public String getBody() {
        StringBuilder body = new StringBuilder();
        body.append("<button class='").append(getCss()).append("' type='button' id='").append(getId()).append("' onclick='");
        body.append(getOnclickEvent()).append("'>");
        body.append("<i class='").append(getIcon()).append("'></i>");
        body.append(getText());
        body.append("</button>");
        return body.toString();
    }
}