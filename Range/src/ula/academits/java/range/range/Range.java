package ula.academits.java.range.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && point <= to;
    }

    public String toString() {
        return String.format("(%.2f; %.2f)", from, to);
    }

    public Range getIntersection(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionFrom < intersectionTo) {
            return new Range(intersectionFrom, intersectionTo);
        }
        return null;
    }

    public Range[] getUnion(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionFrom > intersectionTo) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionFrom >= intersectionTo) {
            return new Range[]{new Range(from, to)};
        }

        if (intersectionFrom == from) {
            if (intersectionTo == to) {
                return new Range[0];
            }
            return new Range[]{new Range(intersectionTo, to)};
        }

        if (intersectionTo == to) {
            return new Range[]{new Range(from, intersectionFrom)};
        }

        return new Range[]{new Range(from, intersectionFrom), new Range(intersectionTo, to)};
    }
}
