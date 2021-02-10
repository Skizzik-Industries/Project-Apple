package net.uskizzik.skizzik.world;

import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import java.lang.reflect.Method;

@SkizzikModElements.ModElement.Tag
public class ConvertSkizziesGameRule extends SkizzikModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("convertSkizzies", GameRules.Category.MOBS,
			create(true));
	public ConvertSkizziesGameRule(SkizzikModElements instance) {
		super(instance, 263);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
