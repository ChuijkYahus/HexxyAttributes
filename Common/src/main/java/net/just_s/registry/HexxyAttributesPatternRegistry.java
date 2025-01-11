package net.just_s.registry;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import kotlin.Triple;
import net.just_s.casting.patterns.OpDomainReflection;
import net.just_s.casting.patterns.OpMediaReflection;
import net.just_s.casting.patterns.OpMindPurification;
import net.just_s.casting.patterns.OpSentinelDomainReflection;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.just_s.HexxyAttributesMod.id;

public class HexxyAttributesPatternRegistry {
    public static List<Triple<HexPattern, Identifier, Action>> PATTERNS = new ArrayList<>();
    public static List<Triple<HexPattern, Identifier, Action>> PER_WORLD_PATTERNS = new ArrayList<>();

    public static HexPattern DOMAIN_REFLECTION = register(HexPattern.fromAngles("qaqeaa", HexDir.NORTH_EAST), "domain_reflection", new OpDomainReflection());
    public static HexPattern SENTINEL_DOMAIN_REFLECTION = register(HexPattern.fromAngles("aeawaeadaa", HexDir.EAST), "sentinel_domain_reflection", new OpSentinelDomainReflection());
    public static HexPattern MEDIA_REFLECTION = register(HexPattern.fromAngles("wwaqwqeaa", HexDir.NORTH_EAST), "media_reflection", new OpMediaReflection());
    public static HexPattern MIND_PURIFICATION = register(HexPattern.fromAngles("waaqa", HexDir.EAST), "mind_purification", new OpMindPurification());

    public static void init() {
        try {
            for (Triple<HexPattern, Identifier, Action> patternTriple : PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird());
            }
            for (Triple<HexPattern, Identifier, Action> patternTriple : PER_WORLD_PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird(), true);
            }
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }

    private static HexPattern register(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PATTERNS.add(triple);
        return pattern;
    }

    private static HexPattern registerPerWorld(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PER_WORLD_PATTERNS.add(triple);
        return pattern;
    }
}
