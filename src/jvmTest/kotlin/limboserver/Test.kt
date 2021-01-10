package limboserver

import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.add
import dev.einsjannis.acacia.protocol.io.net.Server
import dev.einsjannis.acacia.protocol.nbtCompoundTag
import dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake
import dev.einsjannis.acacia.protocol.packet.login.clientbound.LoginSuccess
import dev.einsjannis.acacia.protocol.packet.login.serverbound.LoginStart
import dev.einsjannis.acacia.protocol.packet.play.clientbound.DeclareCommands
import dev.einsjannis.acacia.protocol.packet.play.clientbound.JoinGame
import dev.einsjannis.acacia.protocol.packet.play.clientbound.PlayerAbilities
import dev.einsjannis.acacia.protocol.packet.play.clientbound.PlayerAbilityFlags
import dev.einsjannis.acacia.protocol.packet.play.clientbound.PlayerPositionAndLook
import dev.einsjannis.acacia.protocol.packet.play.clientbound.ServerDifficulty
import dev.einsjannis.acacia.protocol.packet.play.clientbound.SpawnPosition
import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.UUID
import dev.einsjannis.acacia.protocol.primitives.nbt.CompoundTag
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTypeId
import dev.einsjannis.acacia.protocol.types.Difficulty
import dev.einsjannis.acacia.protocol.types.Gamemode
import dev.einsjannis.acacia.protocol.types.Position
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ClientData(var username: String? = null) {
}

class Test(val scope: CoroutineScope, ip: String, port: Int) {
    val server = Server(scope, ip, port, ::ClientData)
    val owoWorld = Identifier("minecraft", "overworld")
    fun run() {
        server.run()
        scope.launch {
            for ((client, packet) in server.incomingPackets) {
                when (packet) {
                    is Handshake -> client.connectionState = packet.nextState
                    is LoginStart -> {
                        client.data.username = packet.name
                        client.send(LoginSuccess().also {
                            it.username = packet.name
                            it.uniqueId = UUID(0L, 0L)
                        })
                        client.connectionState = ConnectionState.PLAY

                        client.send(JoinGame().also {
                            it.entityId = 1
                            it.isHardcore = false
                            it.gamemode = Gamemode.ADVENTURE
                            it.previousGamemode = null
                            it.worldNames = listOf(owoWorld)
                            it.dimensionCodec = nbtCompoundTag {
                                compound("minecraft:dimension_type") {
                                    string("type", "minecraft:dimension_type")
                                    list<CompoundTag>("value", NbtTypeId.COMPOUND) {
                                        add {
                                            string("name", "minecraft:overworld")
                                            int("id", 0)
                                            compound("element") {
                                                byte("piglin_safe", 0)
                                                byte("natural", 1)
                                                float("ambient_light", 0f)
                                                string("infiniburn", "minecraft:infiniburn_overworld")
                                                byte("respawn_anchor_works", 0)
                                                byte("has_skylight", 1)
                                                byte("bed_works", 1)
                                                string("effects", "minecraft:overworld")
                                                byte("has_raids", 1)
                                                int("logical_height", 256)
                                                double("coordinate_scale", 1.0)
                                                byte("ultrawarm", 0)
                                                byte("has_ceiling", 0)
                                            }

                                        }
                                    }
                                }
                                compound("minecraft:worldgen/biome") {
                                    string("type", "minecraft:worldgen/biome")
                                    list<CompoundTag>("value", NbtTypeId.COMPOUND) {
                                        add {
                                            string("name", "minecraft:plains")
                                            int("id", 1)
                                            compound("element") {
                                                string("precipation", "rain")
                                                compound("effects") {
                                                    int("skycolor", 7907327)
                                                    int("waterfogcolor", 329011)
                                                    int("fog_color", 12638463)
                                                    int("water_color", 4159204)
                                                    compound("mood_sound") {
                                                        int("tick_delay", 6000)
                                                        double("offset", 2.0)
                                                        string("sound", "minecraft:ambient_cave")
                                                        int("block_search_extent", 8)
                                                    }
                                                }
                                                float("depth", 0.125f)
                                                float("temperature", 0.8f)
                                                float("scale", 0.05f)
                                                float("downfall", 0.4f)
                                                string("category", "plains")
                                            }
                                        }
                                    }
                                }
                            }
                            it.dimension = nbtCompoundTag {
                                string("name", "minecraft:overworld")
                                int("id", 0)
                                compound("element") {
                                    byte("piglin_safe", 0)
                                    byte("natural", 1)
                                    float("ambient_light", 0f)
                                    string("infiniburn", "minecraft:infiniburn_overworld")
                                    byte("respawn_anchor_works", 0)
                                    byte("has_skylight", 1)
                                    byte("bed_works", 1)
                                    string("effects", "minecraft:overworld")
                                    byte("has_raids", 1)
                                    int("logical_height", 256)
                                    double("coordinate_scale", 1.0)
                                    byte("ultrawarm", 0)
                                    byte("has_ceiling", 0)
                                }
                            }
                            it.worldName = owoWorld
                            it.hashedSeed = 0L
                            it.maxPlayers = 0
                            it.viewDistance = 2
                            it.reducedDebugInfo = true
                            it.enableRespawnScreen = false
                            it.isDebug = false
                            it.isFlat = true
                        })
                        client.send(ServerDifficulty().also {
                            it.difficulty = Difficulty.PEACEFUL
                            it.locked = true
                        })
                        client.send(SpawnPosition().also {
                            it.location = Position(0, 100, 0)
                        })
                        client.send(PlayerAbilities().also {
                            it.fieldOfViewMod = 0.1f
                            it.flyingSpeed = 0.1f
                            it.flags = PlayerAbilityFlags(0)
                        })
                        client.send(PlayerPositionAndLook().also {
                            it.flags = 0
                            it.pitch = 0f
                            it.yaw = 0f
                            it.x = 0.0
                            it.y = 100.0
                            it.z = 0.0
                            it.teleportId = 0 // TODO check for confirm
                        })
                        client.send(DeclareCommands().also {
                            it.count = 0
                            it.nodes = listOf()
                            it.rootIndex = 0
                        })
                        // TODO send spawn chunks
                    }

                }
            }
        }
    }
}
