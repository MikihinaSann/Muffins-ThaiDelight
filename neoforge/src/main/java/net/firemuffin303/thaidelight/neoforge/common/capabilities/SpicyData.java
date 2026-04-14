package net.firemuffin303.thaidelight.neoforge.common.capabilities;

import com.mojang.serialization.Codec;

public class SpicyData implements ISpicy {
    public static final Codec<SpicyData> CODEC = Codec.INT
            .xmap(SpicyData::new, SpicyData::getTimer);

    private int timer;

    public SpicyData() {
        this.timer = 0;
    }

    public SpicyData(int timer) {
        this.timer = timer;
    }

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public void setTimer(int value) {
        this.timer = value;
    }

    @Override
    public void addTimer(int value) {
        this.timer += value;
    }
}
