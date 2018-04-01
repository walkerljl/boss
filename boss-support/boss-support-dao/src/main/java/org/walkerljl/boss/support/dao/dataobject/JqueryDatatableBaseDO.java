package org.walkerljl.boss.support.dao.dataobject;

/**
 * DataTable插件分页参数
 *
 * @author lijunlin
 */
public abstract class JqueryDatatableBaseDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * DataTable请求服务器端次数
     */
    private String sEcho;
    /**
     * 过滤文本
     */
    private String sSearch;
    /**
     * 每页显示的数量
     */
    private int iDisplayLength;
    /**
     * 分页时开始行号
     */
    private int iDisplayStart;
    /**
     * 列数
     */
    private int iColumns;
    /**
     * 排序列的数量
     */
    private int iSortingCols;
    /**
     * 逗号分割所有的列
     */
    private String sColumns;

    //扩展字段

    public JqueryDatatableBaseDO() {}

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    public Integer getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiColumns() {
        return iColumns;
    }

    public void setiColumns(int iColumns) {
        this.iColumns = iColumns;
    }

    public int getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(int iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }
}