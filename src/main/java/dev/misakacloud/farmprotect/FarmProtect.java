package dev.misakacloud.farmprotect;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "farmprotect", name = "FarmProtect", description = "Protect farms")
public class FarmProtect {
    @Inject
    private Logger logger;

    private Logger log() {
        return logger;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        log().info("农场保护插件加载完成");
    }

    //    @Listener
//    public void onPlayerChangeBlock(ChangeBlockEvent.Place e, @Root Player p) {
//        for (Transaction<BlockSnapshot> t : e.getTransactions()) {
//            if (t.getOriginal().getState().getType() == BlockTypes.FARMLAND
//                    && t.getDefault().getState().getType() == BlockTypes.DIRT
//                    && t.getOriginal().getPosition().add(0, 1, 0).equals(p.getPosition().toInt())) {
//                t.setValid(false);
//            }
//        }
//    }
    @Listener
    public void onEntityChangeBlock(ChangeBlockEvent.Place e, @Root Entity p) {
        for (Transaction<BlockSnapshot> t : e.getTransactions()) {
            if (t.getOriginal().getState().getType() == BlockTypes.FARMLAND
                    && t.getDefault().getState().getType() == BlockTypes.DIRT
                    && t.getOriginal().getPosition().add(0, 1, 0).equals(p.getLocation().getPosition().toInt())) {
                t.setValid(false);
                log().debug("已拦截农田踩踏");
            }
        }
    }

}
