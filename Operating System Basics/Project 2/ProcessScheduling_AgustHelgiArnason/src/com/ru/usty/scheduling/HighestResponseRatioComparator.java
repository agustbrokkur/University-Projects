package com.ru.usty.scheduling;

import java.util.Comparator;

public class HighestResponseRatioComparator implements Comparator<ProcessData> {
	@Override
	public int compare(ProcessData x, ProcessData y) {
        if (x.ratio < y.ratio)
        {
            return 1;
        }
        if (x.ratio > y.ratio)
        {
            return -1;
        }
        return 0;
	}
}
