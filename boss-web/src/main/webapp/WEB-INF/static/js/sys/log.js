/**
 * log.js
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
                $$.TABLE.pushQueryCondition(aoData, "keyword", "s_keyword");
            },
            aoColumns: [
                {
                    "mData": "id", mRender: function (data, type, row) {
                    return $$.TABLE.getIdColumn(data);
                }
                },
                {"mData": "id"},
                {"mData": "appCode"},
                {"mData": "traceId"},
                {"mData": "keyword"},
                {"mData": "request"},
                {"mData": "response"},
                {"mData": "operator"},
                {"mData": "operated"}
            ]
        });
    });
})(GLOBAL_NS, "sys.log");