package ula.academits.java.range;

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

    public boolean isExactlyInside(double point) {
        return point >= from && point <= to;
    }

    public boolean isInside(double point) {
        return point > from && point < to;
    }

    public void println() {
        System.out.printf("(%.2f;%.2f)%n", from, to);
    }

    public void println(Range[] arrayRange) {
        for (int i = 0; i < arrayRange.length; i++) {
            System.out.printf("(%.2f;%.2f)%n", arrayRange[i].from, arrayRange[i].to);
        }
    }

    public Range getIntersection(Range range2) {
        if (isInside(range2.getFrom())) {
            if (isInside(range2.getTo())) {
                return new Range(range2.getFrom(), range2.getTo());
            } else {
                return new Range(range2.getFrom(), this.to);
            }
        } else if (range2.isInside(this.from)) {
            if (range2.isInside(this.to)) {
                return new Range(this.from, this.to);
            } else {
                return new Range(this.from, range2.getTo());
            }
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range range2) {
        if (isExactlyInside(range2.getFrom())) {
            if (isExactlyInside(range2.getTo())) {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(this.from, this.to);
                return arrayRange;
            } else {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(this.from, range2.getTo());
                return arrayRange;
            }
        } else if (range2.isExactlyInside(this.from)) {
            if (range2.isExactlyInside(this.to)) {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(range2.getFrom(), range2.getTo());
                return arrayRange;
            } else {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(range2.getFrom(), this.to);
                return arrayRange;
            }
        } else {
            Range[] arrayRange = new Range[2];
            arrayRange[0] = new Range(this.from, this.to);
            arrayRange[1] = range2;
            return arrayRange;
        }
    }

    public Range[] getSubtraction(Range range2) {
        if (isInside(range2.getFrom())) {
            if (isInside(range2.getTo())) {
                Range[] arrayRange = new Range[2];
                arrayRange[0] = new Range(this.from, range2.getFrom());
                arrayRange[1] = new Range(range2.getTo(), this.to);
                return arrayRange;
            } else {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(this.from, range2.getFrom());
                return arrayRange;
            }
        } else if (range2.isInside(this.from)) {
            if (range2.isInside(this.to)) {
                return null;
            } else {
                Range[] arrayRange = new Range[1];
                arrayRange[0] = new Range(range2.getTo(), this.to);
                return arrayRange;
            }
        } else {
            Range[] arrayRange = new Range[1];
            arrayRange[0] = new Range(this.from, this.to);
            return arrayRange;
        }
    }
}





