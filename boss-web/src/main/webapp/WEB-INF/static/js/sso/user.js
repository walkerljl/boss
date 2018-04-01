/**
 * user.js
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
                $$.TABLE.pushQueryCondition(aoData, "status", "s_status");
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
                {"mData": "alias"},
                {"mData": "statusName"},
                {"mData": "modified"},
                {"mData": "modifier"}
            ]
        });
    });
})(GLOBAL_NS, "sso.user");