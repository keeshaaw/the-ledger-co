package domain;

import java.util.Objects;

public abstract class BankingEntityLike {
    protected String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankingEntityLike)) return false;
        BankingEntityLike that = (BankingEntityLike) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
