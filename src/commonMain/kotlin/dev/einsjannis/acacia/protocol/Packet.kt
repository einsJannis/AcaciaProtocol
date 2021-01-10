package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.*
import dev.einsjannis.acacia.protocol.packet.status.clientbound.*
import dev.einsjannis.acacia.protocol.packet.status.serverbound.*
import dev.einsjannis.acacia.protocol.packet.login.clientbound.*
import dev.einsjannis.acacia.protocol.packet.login.clientbound.Disconnect as StateDisconnect
import dev.einsjannis.acacia.protocol.packet.login.serverbound.*
import dev.einsjannis.acacia.protocol.packet.play.clientbound.*
import dev.einsjannis.acacia.protocol.packet.play.clientbound.CloseWindow as ClientCloseWindow
import dev.einsjannis.acacia.protocol.packet.play.clientbound.Disconnect as PlayDisconnect
import dev.einsjannis.acacia.protocol.packet.play.clientbound.ChatMessage as ClientChatMessage
import dev.einsjannis.acacia.protocol.packet.play.clientbound.HeldItemChange as ClientHeldItemChange
import dev.einsjannis.acacia.protocol.packet.play.clientbound.KeepAlive as ClientKeepAlive
import dev.einsjannis.acacia.protocol.packet.play.clientbound.PlayerAbilities as ClientPlayerAbilities
import dev.einsjannis.acacia.protocol.packet.play.clientbound.PluginMessage as ClientPluginMessage
import dev.einsjannis.acacia.protocol.packet.play.clientbound.TabComplete as ClientTabComplete
import dev.einsjannis.acacia.protocol.packet.play.clientbound.WindowConfirmation as ClientWindowConfirmation
import dev.einsjannis.acacia.protocol.packet.play.serverbound.*
import dev.einsjannis.acacia.protocol.packet.play.serverbound.CloseWindow as ServerCloseWindow
import dev.einsjannis.acacia.protocol.packet.play.serverbound.ChatMessage as ServerChatMessage
import dev.einsjannis.acacia.protocol.packet.play.serverbound.HeldItemChange as ServerHeldItemChange
import dev.einsjannis.acacia.protocol.packet.play.serverbound.KeepAlive as ServerKeepAlive
import dev.einsjannis.acacia.protocol.packet.play.serverbound.PlayerAbilities as ServerPlayerAbilities
import dev.einsjannis.acacia.protocol.packet.play.serverbound.PluginMessage as ServerPluginMessage
import dev.einsjannis.acacia.protocol.packet.play.serverbound.TabComplete as ServerTabComplete
import dev.einsjannis.acacia.protocol.packet.play.serverbound.WindowConfirmation as ServerWindowConfirmation

/**
 * Base Packet class, used to differentiate between packets and [PacketObject]s
 */
abstract class Packet : PacketObject() {

    /**
     * Companion object of class Packet, containing a list of all [packets] and packet util functions for
     * getting the right packet class from id or vice versa.
     */
    companion object {

        /**
         * Function to read a packet from the [reader] with a certain [id], while during a certain [state] and bound to
         * [bound] and returning it
         */
        suspend fun read(id: Int, state: ConnectionState, bound: Bound, reader: PrimitiveReader): Packet =
            packetMeta(id, state, bound).readPacket(reader)

        /**
         * Function to get [PacketMeta] from packet [id], [state] and [bound]
         */
        fun packetMeta(id: Int, state: ConnectionState, bound: Bound): PacketMeta<out Packet> = packets
            .firstOrNull { it.id == id && it.connectionState == state && it.bound == bound } ?: throw TODO()

        /**
         * Function to write a [packet] to a [writer]
         */
        suspend inline fun <reified T : Packet> write(packet: T, writer: PrimitiveWriter): Unit =
            packetMeta<T>().writePacket(writer, packet)

        /**
         * Function to get [PacketMeta] from [T]
         */
        inline fun <reified T : Packet> packetMeta(): PacketMeta<T> = packets
            .filterIsInstance<PacketMeta<T>>().firstOrNull() ?: throw TODO()

        /**
         * A list of all [Packet]s (actually their respective [PacketMeta])
         */
        val packets = listOf(
            Handshake,
            StateDisconnect,
            EncryptionRequest,
            LoginPluginRequest,
            LoginSuccess,
            SetCompression,
            EncryptionResponse,
            LoginPluginResponse,
            LoginStart,
            Pong,
            Response,
            Ping,
            Request,
            AcknowledgePlayerDigging,
            Advancements,
            AttachEntity,
            BlockAction,
            BlockBreakAnimation,
            BlockChange,
            BlockEntityData,
            BossBar,
            Camera,
            ChangeGameState,
            ClientChatMessage,
            ChunkData,
            ClientCloseWindow,
            CollectItem,
            CombatEvent,
            CraftRecipeResponse,
            DeclareCommands,
            DeclareRecipes,
            DestroyEntities,
            PlayDisconnect,
            DisplayScoreboard,
            Effect,
            EntityAnimation,
            EntityEffect,
            EntityEquipment,
            EntityHeadLook,
            EntityMetadata,
            EntityMovement,
            EntityPosition,
            EntityPositionAndRotation,
            EntityProperties,
            EntityRotation,
            EntitySoundEffect,
            EntityStatus,
            EntityTeleport,
            EntityVelocity,
            Explosion,
            FacePlayer,
            ClientHeldItemChange,
            JoinGame,
            ClientKeepAlive,
            MapData,
            MultiBlockChange,
            NamedSoundEffect,
            NbtQueryResponse,
            OpenBook,
            OpenHorseWindow,
            OpenSignEditor,
            OpenWindow,
            Particle,
            ClientPlayerAbilities,
            PlayerInfo,
            PlayerListHeaderAndFooter,
            PlayerPositionAndLook,
            ClientPluginMessage,
            RemoveEntityEffect,
            ResourcePackSend,
            Respawn,
            ScoreboardObjective,
            SelectAdvancementTab,
            ServerDifficulty,
            SetCooldown,
            SetExperience,
            SetPassanger,
            SetSlot,
            SoundEffect,
            SpawnEntity,
            SpawnExperienceOrb,
            SpawnLivingEntity,
            SpawnPainting,
            SpawnPlayer,
            SpawnPosition,
            Statistics,
            StopSound,
            ClientTabComplete,
            Tags,
            Teams,
            TimeUpdate,
            Title,
            TradeList,
            UnloadChunk,
            UnlockRecipes,
            UpdateHealth,
            UpdateLight,
            UpdateScore,
            UpdateViewDistance,
            UpdateViewPosition,
            VehicleMove,
            ClientWindowConfirmation,
            WindowItems,
            WindowProperty,
            WorldBorder,
            AdvancementTab,
            Animation,
            ServerChatMessage,
            ClickWindow,
            ClickWindowButton,
            ClientSettings,
            ClientStatus,
            ServerCloseWindow,
            CraftRecipeRequest,
            CreativeInventoryAction,
            EditBook,
            EntityAction,
            GenerateStructure,
            ServerHeldItemChange,
            InteractEntity,
            ServerKeepAlive,
            LockDifficulty,
            NameItem,
            PickItem,
            ServerPlayerAbilities,
            PlayerBlockPlacement,
            PlayerDigging,
            PlayerMovement,
            PlayerPosition,
            PlayerPositionAndRotation,
            PlayerRotation,
            ServerPluginMessage,
            QueryBlockNBT,
            QueryEntityNBT,
            ResourcePackStatus,
            SelectTrade,
            SetBeaconEffect,
            SetDifficulty,
            SetDisplayedRecipe,
            SetRecipeBookState,
            Spectate,
            SteerBoat,
            SteerVehicle,
            ServerTabComplete,
            TeleportConfirm,
            UpdateCommandBlock,
            UpdateCommandBlockMinecart,
            UpdateJigsawBlock,
            UpdateSign,
            UpdateStructureBlock,
            UseItem,
            VehicleMovement,
            ServerWindowConfirmation
        )

    }

}
