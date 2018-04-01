/**
 * object.js
 *
 * @author lijunlin
 */
(function ($$, NS) {

    var $$_NS = $$.register(NS);

    $$.MVC.CURD.validateSave = function () {
        var result = true;
        //判断Key是否存在
        if ($$.notEquals($("#oldCode").val(), $("#code").val())) {
            $$.MVC.doRequest($$.MVC.URL.isExists, {code: $("#code").val()}, function (response) {
                var isExists = response[$$.MVC.response["body"]]["isExists"];
                if (isExists) {
                    result = false;
                    $$.alert("code" + $$.MESSAGE.messages["isExists"]);
                }
            }, {async: false});
        }
        //判断name是否存在
        if (result) {
            if ($$.notEquals($("#oldName").val(), $("#name").val())) {
                $$.MVC.doRequest($$.MVC.URL.isExists, {name: $("#name").val()}, function (response) {
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
                $$.TABLE.pushQueryCondition(aoData, "type", "s_type");
                $$.TABLE.pushQueryCondition(aoData, "frequency", "s_frequency");
                $$.TABLE.pushQueryCondition(aoData, "status", "s_status");
            },
            aoColumns: [
                {
                    "mData": "id", mRender: function (data, type, row) {
                    return $$.TABLE.getIdColumn(data);
                }
                },
                {"mData": "id"},
                {"mData": "appName"},
                {"mData": "typeName"},
                {"mData": "target"},
                {"mData": "frequency"},
                {"mData": "statusName"},
                {"mData": "modified"},
                {"mData": "modifier"}
            ]
        });
    });
})(GLOBAL_NS, "mongitor.object");