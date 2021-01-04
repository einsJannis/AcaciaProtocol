package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake
import dev.einsjannis.acacia.protocol.packet.login.clientbound.*
import dev.einsjannis.acacia.protocol.packet.login.clientbound.Disconnect
import dev.einsjannis.acacia.protocol.packet.login.serverbound.EncryptionResponse
import dev.einsjannis.acacia.protocol.packet.login.serverbound.LoginPluginResponse
import dev.einsjannis.acacia.protocol.packet.login.serverbound.LoginStart
import dev.einsjannis.acacia.protocol.packet.play.clientbound.*
import dev.einsjannis.acacia.protocol.packet.status.clientbound.Pong
import dev.einsjannis.acacia.protocol.packet.status.clientbound.Response
import dev.einsjannis.acacia.protocol.packet.status.serverbound.Ping
import dev.einsjannis.acacia.protocol.packet.status.serverbound.Request
import dev.einsjannis.acacia.protocol.primitives.Flags
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

abstract class Packet : PacketObject() {
    companion object {
        val packets = listOf(
            Handshake,
            Disconnect,
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
            ChatMessage,
            ChunkData,
            CloseWindow,
            CollectItem,
            CombatEvent,
            CraftRecipeResponse,
            DeclareCommands,
            DeclareRecipes,
            DestroyEntities,
            dev.einsjannis.acacia.protocol.packet.play.clientbound.Disconnect,
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
            HeldItemChange,
            JoinGame,
            KeepAlive,
            MapData,
            MultiBlockChange,
            NamedSoundEffect,
            NbtQueryResponse,
            OpenBook,
            OpenHorseWindow,
            OpenSignEditor,
            OpenWindow,
            Particle,
            PlayerAbilities,
            PlayerInfo
        )
        suspend fun read(id: Int, state: ConnectionState, bound: Bound, reader: PrimitiveReader): Packet =
            packetMeta(id, state, bound).readPacket(reader)
        fun packetMeta(id: Int, state: ConnectionState, bound: Bound): PacketMeta<out Packet> = packets
            .firstOrNull { it.id == id && it.connectionState == state && it.bound == bound } ?: throw TODO()
        suspend inline fun <reified T : Packet> write(packet: T, state: ConnectionState, bound: Bound, writer: PrimitiveWriter): Unit =
            packetMeta<T>(state, bound).writePacket(writer, packet)
        inline fun <reified T : Packet> packetMeta(state: ConnectionState, bound: Bound): PacketMeta<T> = packets
            .filterIsInstance<PacketMeta<T>>()
            .firstOrNull { it.connectionState == state && it.bound == bound } ?: throw TODO()
    }
}
