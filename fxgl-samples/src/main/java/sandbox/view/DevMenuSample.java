/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package sandbox.view;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.Vec2;
import javafx.beans.binding.Bindings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class DevMenuSample extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 100);
        vars.put("isRed", true);
        vars.put("pos", new Vec2(650, 350));
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {

            System.out.println(getop("pos").hashCode());

            // TODO: check without using "new" and see if updates, if not use forceUpdateListeners()
            set("pos", new Vec2(550, 550));
            System.out.println(geto("pos").toString());
        });
    }

    @Override
    protected void initGame() {
        var text = addVarText("score", 600, 300);
        text.setFont(Font.font(22));
        text.fillProperty().bind(
                Bindings.when(getbp("isRed")).then(Color.RED).otherwise(Color.BLUE)
        );

        getWorldProperties().<Vec2>addListener("pos", (old, pos) -> {
            text.setTranslateX(pos.x);
            text.setTranslateY(pos.y);
        });

        getWorldProperties().<Integer>addListener("score", (old, score) -> {
            System.out.println(score);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
