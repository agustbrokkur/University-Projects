package com.ru.usty.scheduling;
import java.util.Comparator;

public class ShortestProcessComparator implements Comparator<ProcessData> {
	@Override
	public int compare(ProcessData x, ProcessData y) {
        if (x.totalServiceTime < y.totalServiceTime)
        {
            return -1;
        }
        if (x.totalServiceTime > y.totalServiceTime)
        {
            return 1;
        }
        return 0;
	}
}