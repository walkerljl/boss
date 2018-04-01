package org.walkerljl.boss.web;

/**
 * Percent
 *
 * @author lijunlin
 */
public class Percent {
    public String name;
    public String totalValue;
    public String freeValue;
    public String usedValue;
    public int freePercent;
    public int usedPercent;

    public Percent(String name, String totalValue, String usedValue, String freeValue) {
        this.name = name;
        this.totalValue = totalValue;
        this.usedValue = usedValue;
        this.freeValue = freeValue;
    }

    public String getName() {
        return this.name;
    }

    public String getTotalValue() {
        return this.totalValue;
    }

    public String getFreeValue() {
        return this.freeValue;
    }

    public String getUsedValue() {
        return this.usedValue;
    }

    public int getFreePercent() {
        return this.freePercent;
    }

    public int getUsedPercent() {
        return this.usedPercent;
    }

    public String toString() {
        return "Percent{name=\'" + this.name + '\'' + ", totalValue=\'" + this.totalValue + '\'' + ", freeValue=\'" + this.freeValue + '\'' + ", usedValue=\'" + this.usedValue + '\'' + ", freePercent=" + this.freePercent + ", usedPercent=" + this.usedPercent + '}';
    }
}
