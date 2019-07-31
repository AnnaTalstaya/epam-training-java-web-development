package by.talstaya.task01.comparator;

import by.talstaya.task01.entity.Developer;

import java.util.Comparator;

public class DeveloperStatusComparator implements Comparator<Developer> {
    @Override
    public int compare(final Developer dev1, final Developer dev2) {
        int val1 = dev1.getDeveloperStatus().getIndex();
        int val2 = dev2.getDeveloperStatus().getIndex();
        return Integer.compare(dev1.getDeveloperStatus().getIndex(), dev2.getDeveloperStatus().getIndex());
    }
}
