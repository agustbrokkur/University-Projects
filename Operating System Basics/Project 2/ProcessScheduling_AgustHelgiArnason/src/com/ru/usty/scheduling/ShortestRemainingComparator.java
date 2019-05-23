package com.ru.usty.scheduling;

import java.util.Comparator;

public class ShortestRemainingComparator implements Comparator<ProcessData> {
	@Override
	public int compare(ProcessData x, ProcessData y) {
        if (x.remainingTime < y.remainingTime)
        {
            return -1;
        }
        if (x.remainingTime > y.remainingTime)
        {
            return 1;
        }
        return 0;
	}
}