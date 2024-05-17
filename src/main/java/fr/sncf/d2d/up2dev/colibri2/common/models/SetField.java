package fr.sncf.d2d.up2dev.colibri2.common.models;

import java.util.function.Consumer;
import java.util.function.Function;

public class SetField<T> {
    
    private final boolean isProvided;
    private final T value;

    private SetField(T value, boolean isProvided){
        this.value = value;
        this.isProvided = isProvided;
    }

    public static <O> SetField<O> withNull(){
        return new SetField<>(null, true);
    }
    
    public static <O> SetField<O> noop(){
        return new SetField<O>(null, false);
    }

    public static <O> SetField<O> with(O value){
        assert value != null;
        return new SetField<O>(value, true);
    }

    public <O> SetField<O> map(Function<T, O> mapper){
        return this.isProvided
            ? new SetField<>(mapper.apply(this.value), true)
            : new SetField<>(null, false);
    }

    public T orElse(T defaultValue){
        return this.isProvided
            ? this.value
            : defaultValue;
    }

    public <E extends Exception> void ifProvided(ThrowingConsumer<T, E> consumer) throws E {
        if (!this.isProvided)
            return;
        consumer.accept(this.value);
    }

    @FunctionalInterface
    public static interface ThrowingConsumer<T, E extends Exception> {
        void accept(T value) throws E;
    }
}
