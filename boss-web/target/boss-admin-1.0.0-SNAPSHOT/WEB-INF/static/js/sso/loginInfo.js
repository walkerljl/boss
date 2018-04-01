/**
 * loginInfo.js
 *
 * @author lijunlin
 */
(function ($$, NS) {

    var $$_NS = $$.register(NS);

    /**
     * 初始化dataTable
     */
    $$.addConstructor(function () {
        $$.TABLE.dataTable({
            fnServerParams: function (aoData) {
                $$.TABLE.pushQueryCondition(aoData, "userId", "s_userId");
                $$.TABLE.pushQueryCondition(aoData, "userName", "s_userName");
                $$.TABLE.pushQueryCondition(aoData, "loginIp", "s_loginIp");
            },
            aoColumns: [
                {
                    "mData": "id", mRender: function (data, type, row) {
                    return $$.TABLE.getIdColumn(data);
                }
                },
                {"mData": "id"},
                {"mData": "userId"},
                {"mData": "userName"},
                {"mData": "loginIp"},
                {"mData": "loginAgentName"},
                {"mData": "loginTime"},
                {"mData": "logoutTime"}
            ]
        });
    });
})(GLOBAL_NS, "sso.loginInfo");