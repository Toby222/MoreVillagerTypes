package tech.tobot.morevillagers.base.enums;

import java.util.Locale;

import net.minecraft.util.StringIdentifiable;

@SuppressWarnings({ "NullableProblems", "rawtypes" })
public interface IMoreVillagersEnum extends StringIdentifiable {
    @Override
    default String asString() {
        return ((Enum) this).name().toLowerCase(Locale.ENGLISH);
    }

    default String getCapitalizedName() {
        String name = asString();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
