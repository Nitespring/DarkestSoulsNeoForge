package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.common.item.child.alchemy.Flamesprayer;
import github.nitespring.darkestsouls.common.item.child.alchemy.LanternNormal;
import github.nitespring.darkestsouls.common.item.child.guns.GatlingGun;
import github.nitespring.darkestsouls.common.item.child.guns.Pistol;
import github.nitespring.darkestsouls.common.item.child.guns.Shotgun;
import github.nitespring.darkestsouls.common.item.child.staves.ChaosStaff;
import github.nitespring.darkestsouls.common.item.child.staves.CrystalStaff;
import github.nitespring.darkestsouls.common.item.child.staves.SorcererStaff;
import github.nitespring.darkestsouls.common.item.child.weapons.*;
import github.nitespring.darkestsouls.common.item.child.weapons.trickweapon.*;
import github.nitespring.darkestsouls.common.item.throwing.Firebomb;
import github.nitespring.darkestsouls.common.item.throwing.MolotovCocktail;
import github.nitespring.darkestsouls.common.item.throwing.ThrowingKnife;
import github.nitespring.darkestsouls.core.enums.Tiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK,
			DarkestSouls.MODID);

	//Ores

	public static final DeferredHolder<Block,DropExperienceBlock> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> SIDERITE_ORE = BLOCKS.register("siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f)/*.lightLevel((p_50872_) -> {
				return 1;
			})*/.pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> DEEPSLATE_CINNABAR_ORE = BLOCKS.register("deepslate_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> DEEPSLATE_SIDERITE_ORE = BLOCKS.register("deepslate_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> NETHER_CINNABAR_ORE = BLOCKS.register("nether_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> NETHER_SIDERITE_ORE = BLOCKS.register("nether_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> BLACKSTONE_CINNABAR_ORE = BLOCKS.register("blackstone_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> BLACKSTONE_SIDERITE_ORE = BLOCKS.register("blackstone_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> ENDER_CINNABAR_ORE = BLOCKS.register("ender_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> ENDER_SIDERITE_ORE = BLOCKS.register("ender_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,DropExperienceBlock> BASALT_CINNABAR_ORE = BLOCKS.register("basalt_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.BASALT).requiresCorrectToolForDrops().strength(2.0f,7.0f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> SIDERITE_BRICKS = BLOCKS.register("siderite_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> CRACKED_SIDERITE_BRICKS = BLOCKS.register("cracked_siderite_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> SIDERITE_BRICK_SLAB = BLOCKS.register("siderite_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> SIDERITE_BRICK_STAIRS = BLOCKS.register("siderite_brick_stairs",
			() -> new StairBlock(SIDERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> SIDERITE_BRICK_WALL = BLOCKS.register("siderite_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));


	public static final DeferredHolder<Block,Block> NIGHTMARE_STONE = BLOCKS.register("nightmare_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_STONE_SLAB = BLOCKS.register("nightmare_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_STONE_STAIRS = BLOCKS.register("nightmare_stone_stairs",
			() -> new StairBlock(NIGHTMARE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_STONE_WALL = BLOCKS.register("nightmare_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));
	public static final DeferredHolder<Block,Block> NIGHTMARE_STONE_BRICKS = BLOCKS.register("nightmare_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_STONE_BRICK_SLAB = BLOCKS.register("nightmare_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_STONE_BRICK_STAIRS = BLOCKS.register("nightmare_stone_brick_stairs",
			() -> new StairBlock(NIGHTMARE_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_STONE_BRICK_WALL = BLOCKS.register("nightmare_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));
	public static final DeferredHolder<Block,Block> NIGHTMARE_SMOOTH_STONE_BRICKS = BLOCKS.register("nightmare_smooth_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_SMOOTH_STONE_BRICK_SLAB = BLOCKS.register("nightmare_smooth_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_SMOOTH_STONE_BRICK_STAIRS = BLOCKS.register("nightmare_smooth_stone_brick_stairs",
			() -> new StairBlock(NIGHTMARE_SMOOTH_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_SMOOTH_STONE_BRICK_WALL = BLOCKS.register("nightmare_smooth_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> NIGHTMARE_STONE_DECOR_HUNTER = BLOCKS.register("nightmare_stone_decor_hunter",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> DARK_NIGHTMARE_STONE = BLOCKS.register("dark_nightmare_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_NIGHTMARE_STONE_SLAB = BLOCKS.register("dark_nightmare_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_NIGHTMARE_STONE_STAIRS = BLOCKS.register("dark_nightmare_stone_stairs",
			() -> new StairBlock(DARK_NIGHTMARE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_NIGHTMARE_STONE_WALL = BLOCKS.register("dark_nightmare_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));
	public static final DeferredHolder<Block,Block> DARK_NIGHTMARE_STONE_BRICKS = BLOCKS.register("dark_nightmare_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_NIGHTMARE_STONE_BRICK_SLAB = BLOCKS.register("dark_nightmare_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_NIGHTMARE_STONE_BRICK_STAIRS = BLOCKS.register("dark_nightmare_stone_brick_stairs",
			() -> new StairBlock(DARK_NIGHTMARE_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_NIGHTMARE_STONE_BRICK_WALL = BLOCKS.register("dark_nightmare_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));
	public static final DeferredHolder<Block,Block> DARK_NIGHTMARE_SMOOTH_STONE_BRICKS = BLOCKS.register("dark_nightmare_smooth_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_NIGHTMARE_SMOOTH_STONE_BRICK_SLAB = BLOCKS.register("dark_nightmare_smooth_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_NIGHTMARE_SMOOTH_STONE_BRICK_STAIRS = BLOCKS.register("dark_nightmare_smooth_stone_brick_stairs",
			() -> new StairBlock(DARK_NIGHTMARE_SMOOTH_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_NIGHTMARE_SMOOTH_STONE_BRICK_WALL = BLOCKS.register("dark_nightmare_smooth_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> RAW_COBBLESTONE = BLOCKS.register("raw_cobblestone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> RAW_COBBLESTONE_SLAB = BLOCKS.register("raw_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> RAW_COBBLESTONE_STAIRS = BLOCKS.register("raw_cobblestone_stairs",
			() -> new StairBlock(RAW_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> RAW_COBBLESTONE_WALL = BLOCKS.register("raw_cobblestone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> RAW_STONE_BRICKS = BLOCKS.register("raw_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> RAW_STONE_BRICK_SLAB = BLOCKS.register("raw_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> RAW_STONE_BRICK_STAIRS = BLOCKS.register("raw_stone_brick_stairs",
			() -> new StairBlock(RAW_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> RAW_STONE_BRICK_WALL = BLOCKS.register("raw_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> DARK_GLINTSTONE_BRICKS = BLOCKS.register("dark_glintstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_GLINTSTONE_BRICK_SLAB = BLOCKS.register("dark_glintstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_GLINTSTONE_BRICK_STAIRS = BLOCKS.register("dark_glintstone_brick_stairs",
			() -> new StairBlock(DARK_GLINTSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_GLINTSTONE_BRICK_WALL = BLOCKS.register("dark_glintstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_GLINTSTONE_BRICKS = BLOCKS.register("light_glintstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_GLINTSTONE_BRICK_SLAB = BLOCKS.register("light_glintstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_GLINTSTONE_BRICK_STAIRS = BLOCKS.register("light_glintstone_brick_stairs",
			() -> new StairBlock(LIGHT_GLINTSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_GLINTSTONE_BRICK_WALL = BLOCKS.register("light_glintstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> GREEN_GLINTSTONE_BRICKS = BLOCKS.register("green_glintstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> GREEN_GLINTSTONE_BRICK_SLAB = BLOCKS.register("green_glintstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> GREEN_GLINTSTONE_BRICK_STAIRS = BLOCKS.register("green_glintstone_brick_stairs",
			() -> new StairBlock(GREEN_GLINTSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> GREEN_GLINTSTONE_BRICK_WALL = BLOCKS.register("green_glintstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> DARK_GLINTSTONE_TILES = BLOCKS.register("dark_glintstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_GLINTSTONE_TILES_SLAB = BLOCKS.register("dark_glintstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_GLINTSTONE_TILES_STAIRS = BLOCKS.register("dark_glintstone_tiles_stairs",
			() -> new StairBlock(DARK_GLINTSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_GLINTSTONE_TILES_WALL = BLOCKS.register("dark_glintstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_GLINTSTONE_TILES = BLOCKS.register("light_glintstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_GLINTSTONE_TILES_SLAB = BLOCKS.register("light_glintstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_GLINTSTONE_TILES_STAIRS = BLOCKS.register("light_glintstone_tiles_stairs",
			() -> new StairBlock(LIGHT_GLINTSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_GLINTSTONE_TILES_WALL = BLOCKS.register("light_glintstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> GREEN_GLINTSTONE_TILES = BLOCKS.register("green_glintstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> GREEN_GLINTSTONE_TILES_SLAB = BLOCKS.register("green_glintstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> GREEN_GLINTSTONE_TILES_STAIRS = BLOCKS.register("green_glintstone_tiles_stairs",
			() -> new StairBlock(GREEN_GLINTSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> GREEN_GLINTSTONE_TILES_WALL = BLOCKS.register("green_glintstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> PURPLE_CRYSTAL_BRICKS = BLOCKS.register("purple_crystal_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> PURPLE_CRYSTAL_BRICK_SLAB = BLOCKS.register("purple_crystal_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> PURPLE_CRYSTAL_BRICK_STAIRS = BLOCKS.register("purple_crystal_brick_stairs",
			() -> new StairBlock(PURPLE_CRYSTAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> PURPLE_CRYSTAL_BRICK_WALL = BLOCKS.register("purple_crystal_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> PURPLE_CRYSTAL_TILES = BLOCKS.register("purple_crystal_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> PURPLE_CRYSTAL_TILES_SLAB = BLOCKS.register("purple_crystal_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> PURPLE_CRYSTAL_TILES_STAIRS = BLOCKS.register("purple_crystal_tiles_stairs",
			() -> new StairBlock(PURPLE_CRYSTAL_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> PURPLE_CRYSTAL_TILES_WALL = BLOCKS.register("purple_crystal_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,ColoredFallingBlock> LORAN_SAND = BLOCKS.register("loran_sand",
			() -> new ColoredFallingBlock(new ColorRGBA(17387411), BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,ColoredFallingBlock> NIGHTMARE_SAND = BLOCKS.register("nightmare_sand",
			() -> new ColoredFallingBlock(new ColorRGBA(1161191281), BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE = BLOCKS.register("loran_sandstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SANDSTONE_SLAB = BLOCKS.register("loran_sandstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SANDSTONE_STAIRS = BLOCKS.register("loran_sandstone_stairs",
			() -> new StairBlock(LORAN_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SANDSTONE_WALL = BLOCKS.register("loran_sandstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_BRICKS = BLOCKS.register("loran_sandstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SANDSTONE_BRICK_SLAB = BLOCKS.register("loran_sandstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SANDSTONE_BRICK_STAIRS = BLOCKS.register("loran_sandstone_brick_stairs",
			() -> new StairBlock(LORAN_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SANDSTONE_BRICK_WALL = BLOCKS.register("loran_sandstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_TILES = BLOCKS.register("loran_sandstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SANDSTONE_TILES_SLAB = BLOCKS.register("loran_sandstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SANDSTONE_TILES_STAIRS = BLOCKS.register("loran_sandstone_tiles_stairs",
			() -> new StairBlock(LORAN_SANDSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SANDSTONE_TILES_WALL = BLOCKS.register("loran_sandstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_COBBLESTONE = BLOCKS.register("loran_sandstone_cobblestone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SANDSTONE_COBBLESTONE_SLAB = BLOCKS.register("loran_sandstone_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SANDSTONE_COBBLESTONE_STAIRS = BLOCKS.register("loran_sandstone_cobblestone_stairs",
			() -> new StairBlock(LORAN_SANDSTONE_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SANDSTONE_COBBLESTONE_WALL = BLOCKS.register("loran_sandstone_cobblestone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_DECOR_HUNTER = BLOCKS.register("loran_sandstone_decor_hunter",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_ORNATED_BRICKS = BLOCKS.register("loran_sandstone_ornated_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LORAN_SMOOTH_SANDSTONE = BLOCKS.register("loran_smooth_sandstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SMOOTH_SANDSTONE_SLAB = BLOCKS.register("loran_smooth_sandstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SMOOTH_SANDSTONE_STAIRS = BLOCKS.register("loran_smooth_sandstone_stairs",
			() -> new StairBlock(LORAN_SMOOTH_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SMOOTH_SANDSTONE_WALL = BLOCKS.register("loran_smooth_sandstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SMOOTH_SANDSTONE_BRICKS = BLOCKS.register("loran_smooth_sandstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SMOOTH_SANDSTONE_BRICK_SLAB = BLOCKS.register("loran_smooth_sandstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SMOOTH_SANDSTONE_BRICK_STAIRS = BLOCKS.register("loran_smooth_sandstone_brick_stairs",
			() -> new StairBlock(LORAN_SMOOTH_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SMOOTH_SANDSTONE_BRICK_WALL = BLOCKS.register("loran_smooth_sandstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LORAN_SMOOTH_SANDSTONE_TILES = BLOCKS.register("loran_smooth_sandstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LORAN_SMOOTH_SANDSTONE_TILES_SLAB = BLOCKS.register("loran_smooth_sandstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LORAN_SMOOTH_SANDSTONE_TILES_STAIRS = BLOCKS.register("loran_smooth_sandstone_tiles_stairs",
			() -> new StairBlock(LORAN_SMOOTH_SANDSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LORAN_SMOOTH_SANDSTONE_TILES_WALL = BLOCKS.register("loran_smooth_sandstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> NIGHTMARE_COBBLED_STONE_BRICKS = BLOCKS.register("nightmare_cobbled_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_COBBLED_STONE_BRICK_SLAB = BLOCKS.register("nightmare_cobbled_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_COBBLED_STONE_BRICK_STAIRS = BLOCKS.register("nightmare_cobbled_stone_brick_stairs",
			() -> new StairBlock(NIGHTMARE_COBBLED_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_COBBLED_STONE_BRICK_WALL = BLOCKS.register("nightmare_cobbled_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> NIGHTMARE_COBBLESTONE = BLOCKS.register("nightmare_cobblestone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_COBBLESTONE_SLAB = BLOCKS.register("nightmare_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_COBBLESTONE_STAIRS = BLOCKS.register("nightmare_cobblestone_stairs",
			() -> new StairBlock(NIGHTMARE_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_COBBLESTONE_WALL = BLOCKS.register("nightmare_cobblestone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,ColoredFallingBlock> LIGHT_LORAN_SAND = BLOCKS.register("light_loran_sand",
			() -> new ColoredFallingBlock(new ColorRGBA(17387411), BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE = BLOCKS.register("light_loran_sandstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SANDSTONE_SLAB = BLOCKS.register("light_loran_sandstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SANDSTONE_STAIRS = BLOCKS.register("light_loran_sandstone_stairs",
			() -> new StairBlock(LIGHT_LORAN_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SANDSTONE_WALL = BLOCKS.register("light_loran_sandstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_BRICKS = BLOCKS.register("light_loran_sandstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SANDSTONE_BRICK_SLAB = BLOCKS.register("light_loran_sandstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SANDSTONE_BRICK_STAIRS = BLOCKS.register("light_loran_sandstone_brick_stairs",
			() -> new StairBlock(LIGHT_LORAN_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SANDSTONE_BRICK_WALL = BLOCKS.register("light_loran_sandstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_TILES = BLOCKS.register("light_loran_sandstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SANDSTONE_TILES_SLAB = BLOCKS.register("light_loran_sandstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SANDSTONE_TILES_STAIRS = BLOCKS.register("light_loran_sandstone_tiles_stairs",
			() -> new StairBlock(LIGHT_LORAN_SANDSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SANDSTONE_TILES_WALL = BLOCKS.register("light_loran_sandstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_COBBLESTONE = BLOCKS.register("light_loran_sandstone_cobblestone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SANDSTONE_COBBLESTONE_SLAB = BLOCKS.register("light_loran_sandstone_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SANDSTONE_COBBLESTONE_STAIRS = BLOCKS.register("light_loran_sandstone_cobblestone_stairs",
			() -> new StairBlock(LIGHT_LORAN_SANDSTONE_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SANDSTONE_COBBLESTONE_WALL = BLOCKS.register("light_loran_sandstone_cobblestone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_DECOR_HUNTER = BLOCKS.register("light_loran_sandstone_decor_hunter",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_ORNATED_BRICKS = BLOCKS.register("light_loran_sandstone_ornated_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SMOOTH_SANDSTONE = BLOCKS.register("light_loran_smooth_sandstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_SLAB = BLOCKS.register("light_loran_smooth_sandstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_STAIRS = BLOCKS.register("light_loran_smooth_sandstone_stairs",
			() -> new StairBlock(LIGHT_LORAN_SMOOTH_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_WALL = BLOCKS.register("light_loran_smooth_sandstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SMOOTH_SANDSTONE_BRICKS = BLOCKS.register("light_loran_smooth_sandstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_BRICK_SLAB = BLOCKS.register("light_loran_smooth_sandstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_BRICK_STAIRS = BLOCKS.register("light_loran_smooth_sandstone_brick_stairs",
			() -> new StairBlock(LIGHT_LORAN_SMOOTH_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_BRICK_WALL = BLOCKS.register("light_loran_smooth_sandstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SMOOTH_SANDSTONE_TILES = BLOCKS.register("light_loran_smooth_sandstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_TILES_SLAB = BLOCKS.register("light_loran_smooth_sandstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_TILES_STAIRS = BLOCKS.register("light_loran_smooth_sandstone_tiles_stairs",
			() -> new StairBlock(LIGHT_LORAN_SMOOTH_SANDSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> LIGHT_LORAN_SMOOTH_SANDSTONE_TILES_WALL = BLOCKS.register("light_loran_smooth_sandstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> CHAOS_STONE = BLOCKS.register("chaos_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> CHAOS_STONE_SLAB = BLOCKS.register("chaos_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> CHAOS_STONE_STAIRS = BLOCKS.register("chaos_stone_stairs",
			() -> new StairBlock(CHAOS_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> CHAOS_STONE_WALL = BLOCKS.register("chaos_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> SMOOTH_CHAOS_STONE = BLOCKS.register("smooth_chaos_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> SMOOTH_CHAOS_STONE_SLAB = BLOCKS.register("smooth_chaos_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> SMOOTH_CHAOS_STONE_STAIRS = BLOCKS.register("smooth_chaos_stone_stairs",
			() -> new StairBlock(SMOOTH_CHAOS_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> SMOOTH_CHAOS_STONE_WALL = BLOCKS.register("smooth_chaos_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> CHAOS_STONE_BRICKS = BLOCKS.register("chaos_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> CHAOS_STONE_BRICK_SLAB = BLOCKS.register("chaos_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> CHAOS_STONE_BRICK_STAIRS = BLOCKS.register("chaos_stone_brick_stairs",
			() -> new StairBlock(CHAOS_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> CHAOS_STONE_BRICK_WALL = BLOCKS.register("chaos_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> CHAOS_STONE_TILES = BLOCKS.register("chaos_stone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> CHAOS_STONE_TILES_SLAB = BLOCKS.register("chaos_stone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> CHAOS_STONE_TILES_STAIRS = BLOCKS.register("chaos_stone_tiles_stairs",
			() -> new StairBlock(CHAOS_STONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> CHAOS_STONE_TILES_WALL = BLOCKS.register("chaos_stone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> SMOOTH_CHAOS_STONE_BRICKS = BLOCKS.register("smooth_chaos_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> SMOOTH_CHAOS_STONE_BRICK_SLAB = BLOCKS.register("smooth_chaos_stone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> SMOOTH_CHAOS_STONE_BRICK_STAIRS = BLOCKS.register("smooth_chaos_stone_brick_stairs",
			() -> new StairBlock(SMOOTH_CHAOS_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> SMOOTH_CHAOS_STONE_BRICK_WALL = BLOCKS.register("smooth_chaos_stone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> SMOOTH_CHAOS_STONE_TILES = BLOCKS.register("smooth_chaos_stone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> SMOOTH_CHAOS_STONE_TILES_SLAB = BLOCKS.register("smooth_chaos_stone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> SMOOTH_CHAOS_STONE_TILES_STAIRS = BLOCKS.register("smooth_chaos_stone_tiles_stairs",
			() -> new StairBlock(SMOOTH_CHAOS_STONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> SMOOTH_CHAOS_STONE_TILES_WALL = BLOCKS.register("smooth_chaos_stone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> DEEPSTONE = BLOCKS.register("deepstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> GRIMSTONE = BLOCKS.register("grimstone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> DEEPSTONE_BRICKS = BLOCKS.register("deepstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> GRIMSTONE_BRICKS = BLOCKS.register("grimstone_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> DEEPSTONE_TILES = BLOCKS.register("deepstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> GRIMSTONE_TILES = BLOCKS.register("grimstone_tiles",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,SlabBlock> DEEPSTONE_SLAB = BLOCKS.register("deepstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DEEPSTONE_STAIRS = BLOCKS.register("deepstone_stairs",
			() -> new StairBlock(DEEPSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DEEPSTONE_WALL = BLOCKS.register("deepstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,SlabBlock> GRIMSTONE_SLAB = BLOCKS.register("grimstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> GRIMSTONE_STAIRS = BLOCKS.register("grimstone_stairs",
			() -> new StairBlock(GRIMSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> GRIMSTONE_WALL = BLOCKS.register("grimstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,SlabBlock> DEEPSTONE_BRICK_SLAB = BLOCKS.register("deepstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DEEPSTONE_BRICK_STAIRS = BLOCKS.register("deepstone_brick_stairs",
			() -> new StairBlock(DEEPSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DEEPSTONE_BRICK_WALL = BLOCKS.register("deepstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,SlabBlock> GRIMSTONE_BRICK_SLAB = BLOCKS.register("grimstone_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> GRIMSTONE_BRICK_STAIRS = BLOCKS.register("grimstone_brick_stairs",
			() -> new StairBlock(GRIMSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> GRIMSTONE_BRICK_WALL = BLOCKS.register("grimstone_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,SlabBlock> DEEPSTONE_TILES_SLAB = BLOCKS.register("deepstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DEEPSTONE_TILES_STAIRS = BLOCKS.register("deepstone_tiles_stairs",
			() -> new StairBlock(DEEPSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DEEPSTONE_TILES_WALL = BLOCKS.register("deepstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,SlabBlock> GRIMSTONE_TILES_SLAB = BLOCKS.register("grimstone_tiles_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> GRIMSTONE_TILES_STAIRS = BLOCKS.register("grimstone_tiles_stairs",
			() -> new StairBlock(GRIMSTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> GRIMSTONE_TILES_WALL = BLOCKS.register("grimstone_tiles_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> DARK_NIGHTMARE_SMOOTH_STONE = BLOCKS.register("dark_nightmare_smooth_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> DARK_NIGHTMARE_SMOOTH_STONE_SLAB = BLOCKS.register("dark_nightmare_smooth_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> DARK_NIGHTMARE_SMOOTH_STONE_STAIRS = BLOCKS.register("dark_nightmare_smooth_stone_stairs",
			() -> new StairBlock(DARK_NIGHTMARE_SMOOTH_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> DARK_NIGHTMARE_SMOOTH_STONE_WALL = BLOCKS.register("dark_nightmare_smooth_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,Block> NIGHTMARE_SMOOTH_STONE = BLOCKS.register("nightmare_smooth_stone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,SlabBlock> NIGHTMARE_SMOOTH_STONE_SLAB = BLOCKS.register("nightmare_smooth_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,StairBlock> NIGHTMARE_SMOOTH_STONE_STAIRS = BLOCKS.register("nightmare_smooth_stone_stairs",
			() -> new StairBlock(NIGHTMARE_SMOOTH_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,WallBlock> NIGHTMARE_SMOOTH_STONE_WALL = BLOCKS.register("nightmare_smooth_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

	public static final DeferredHolder<Block,RotatedPillarBlock> CHAOS_STONE_PILLAR = BLOCKS.register("chaos_stone_pillar",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> CHAOS_STONE_BRICK_KEYSTONE = BLOCKS.register("chaos_stone_brick_keystone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> CHAOS_STONE_BRICK_CROSSBEAM = BLOCKS.register("chaos_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_CHAOS_STONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_chaos_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> DARK_NIGHTMARE_STONE_BRICK_CROSSBEAM = BLOCKS.register("dark_nightmare_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_DARK_NIGHTMARE_STONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_dark_nightmare_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> NIGHTMARE_STONE_BRICK_CROSSBEAM = BLOCKS.register("nightmare_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_NIGHTMARE_STONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_nightmare_stone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> DEEPSTONE_BRICK_KEYSTONE = BLOCKS.register("deepstone_brick_keystone",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DEEPSTONE_BRICK_CROSSBEAM = BLOCKS.register("deepstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_DEEPSTONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_deepstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> GRIMSTONE_BRICK_CROSSBEAM = BLOCKS.register("grimstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_GRIMSTONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_grimstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LORAN_SANDSTONE_BRICK_CROSSBEAM = BLOCKS.register("loran_sandstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_LORAN_SANDSTONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_loran_sandstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

	public static final DeferredHolder<Block,Block> LIGHT_LORAN_SANDSTONE_BRICK_CROSSBEAM = BLOCKS.register("light_loran_sandstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final DeferredHolder<Block,Block> DECORATED_LIGHT_LORAN_SANDSTONE_BRICK_CROSSBEAM = BLOCKS.register("decorated_light_loran_sandstone_brick_crossbeam",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));

}
