package net.firemuffin303.thaidelight.neoforge.common.capabilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class DurianHeatData implements IDurianHeat {
    public static final Codec<DurianHeatData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("timer").forGetter(DurianHeatData::getTimer),
                    Codec.BOOL.fieldOf("heat_up").forGetter(DurianHeatData::isHeatUp)
            ).apply(instance, DurianHeatData::new));

    private int timer;
    private boolean heatUp;

    public DurianHeatData() {
        this.timer = 0;
        this.heatUp = false;
    }

    public DurianHeatData(int timer, boolean heatUp) {
        this.timer = timer;
        this.heatUp = heatUp;
    }

    @Override
    public void setTimer(int value) {
        this.timer = value;
    }

    @Override
    public void addTimer(int value) {
        this.timer += value;
    }

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public void setHeat(boolean value) {
        this.heatUp = value;
    }

    @Override
    public boolean isHeatUp() {
        return heatUp;
    }
}
