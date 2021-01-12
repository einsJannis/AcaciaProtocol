package limboserver

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.io.net.Server
import dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake
import dev.einsjannis.acacia.protocol.packet.login.clientbound.LoginSuccess
import dev.einsjannis.acacia.protocol.packet.login.serverbound.LoginStart
import dev.einsjannis.acacia.protocol.packet.play.clientbound.*
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
    val owoWorld = Identifier("acacia", "limbo")
    fun run() {
        server.run()
        scope.launch {
            for ((client, packet) in server.incomingPackets) {
                when (packet) {
                    is Handshake -> client.connectionState = packet.nextState
                    is LoginStart -> {
                        client.data.username = packet.name
                        client.send(LoginSuccess.build {
                            username = packet.name
                            uniqueId = UUID(0L, 0L)
                        })
                        client.connectionState = ConnectionState.PLAY

                        client.send(JoinGame.build {
                            entityId = 1
                            isHardcore = false
                            gamemode = Gamemode.ADVENTURE
                            previousGamemode = null
                            worldNames = listOf(owoWorld)
                            dimensionCodec = nbtCompoundTag {
                                compound("minecraft:dimension_type") {
                                    string("type", "minecraft:dimension_type")
                                    list<CompoundTag>("value", NbtTypeId.COMPOUND) {
                                        add {
                                            string("name", owoWorld.toString())
                                            int("id", 0)
                                            compound("element") {
                                                byte("piglin_safe", 0)
                                                byte("natural", 1)
                                                float("ambient_light", 0f)
                                                string("infiniburn", "minecraft:infiniburn_overworld")
                                                byte("respawn_anchor_works", 0)
                                                byte("has_skylight", 1)
                                                byte("bed_works", 1)
                                                string("effects", "minecraft:end")
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
                                            string("name", "acacia:limbo")
                                            int("id", 1)
                                            compound("element") {
                                                string("precipation", "rain")
                                                compound("effects") {
                                                    int("skycolor", 0)
                                                    int("waterfogcolor", 0)
                                                    int("fog_color", 0)
                                                    int("water_color", 0)
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
                            dimension = nbtCompoundTag {
                                string("name", owoWorld.toString())
                                int("id", 0)
                                compound("element") {
                                    byte("piglin_safe", 0)
                                    byte("natural", 1)
                                    float("ambient_light", 0f)
                                    string("infiniburn", "minecraft:infiniburn_overworld")
                                    byte("respawn_anchor_works", 0)
                                    byte("has_skylight", 1)
                                    byte("bed_works", 1)
                                    string("effects", "minecraft:end")
                                    byte("has_raids", 1)
                                    int("logical_height", 256)
                                    double("coordinate_scale", 1.0)
                                    byte("ultrawarm", 0)
                                    byte("has_ceiling", 0)
                                }
                            }
                            worldName = owoWorld
                            hashedSeed = 0L
                            maxPlayers = 0
                            viewDistance = 2
                            reducedDebugInfo = true
                            enableRespawnScreen = false
                            isDebug = false
                            isFlat = true
                        })
                        client.send(ServerDifficulty.build {
                            difficulty = Difficulty.PEACEFUL
                            locked = true
                        })
                        client.send(SpawnPosition.build {
                            location = Position(0, 100, 0)
                        })
                        client.send(PlayerAbilities.build {
                            fieldOfViewMod = 0.1f
                            flyingSpeed = 0.1f
                            flags = PlayerAbilityFlags(0)
                        })
                        client.send(PlayerPositionAndLook.build {
                            flags = 0
                            pitch = 0f
                            yaw = 0f
                            x = 0.0
                            y = 100.0
                            z = 0.0
                            teleportId = 0 // TODO check for confirm
                        })
                        client.send(DeclareCommands.build {
                            count = 0
                            nodes = listOf()
                            rootIndex = 0
                        })
                        //TODO: send chunk data + rework chunk data
                    }

                }
            }
        }
    }
}
