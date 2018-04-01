/**
 * config.js
 *
 * @author lijunlin
 */
(function ($$, NS) {

    var $$_NS = $$.register(NS);

    $$.MVC.CURD.validateSave = function () {
        var result = true;
        //判断Key是否存在
        if ($$.notEquals($("#oldKey").val(), $("#key").val())) {
            $$.MVC.doRequest($$.MVC.URL.isExists, {key: $("#key").val()}, function (response) {
                var isExists = response[$$.MVC.response["body"]]["isExists"];
                if (isExists) {
                    result = false;
                    $$.alert("key" + $$.MESSAGE.messages["isExists"]);
                }
            }, {async: false});
        }
        //判断name是否存在
        if (result) {
            if ($$.notEquals($("#oldName").val(), $("#name").val())) {
                $$.MVC.doRequest($$.MVC.URL.isExists, {key: $("#name").val()}, function (response) {
                    var isExists = response[$$.MVC.response["body"]]["isExists"];
                    if (isExists) {
                        result = false;
                        $$.alert("name" + $$.MESSAGE.messages["isExists"]);
                    }
                }, {async: false});
            }
        }
        return result;
    }

    /**
     * 初始化dataTable
     */
    $$.addConstructor(function () {
        $$.TABLE.dataTable({
            fnServerParams: function (aoData) {
                $$.TABLE.pushQueryCondition(aoData, "appId", "s_appId");
                $$.TABLE.pushQueryCondition(aoData, "key", "s_key");
                $$.TABLE.pushQueryCondition(aoData, "name", "s_name");
                $$.TABLE.pushQueryCondition(aoData, "status", "s_status");
            },
            aoColumns: [
                {
                    "mData": "id", mRender: function (data, type, row) {
                    return $$.TABLE.getIdColumn(data);
                }
                },
                {"mData": "id"},
                {"mData": "appId"},
                {"mData": "name"},
                {"mData": "key"},
                {"mData": "statusName"},
                {"mData": "modified"},
                {"mData": "modifier"}
            ]
        });
    });
})(GLOBAL_NS, "sys.config");