package util;

import core.terrain.Vec;

import java.util.ArrayList;

public class PerlinSampler {
    public float scale = 1;
    public ArrayList<Vec> memoizedKeys = new ArrayList<>();
    public ArrayList<Vec> memoizedValues = new ArrayList<>();


    public PerlinSampler(float scale){
        this.scale = scale;
    }
    public float sampleNoise(Vec position){
        int minX = (int)(position.x*scale);
        int minY = (int)(position.y*scale);

        Vec offset = new Vec(position.x*scale - minX, position.y*scale-minY);

        float minDot = Vec.dotProduct(getVectorFor(minX, minY),  offset);
        float xDot = Vec.dotProduct(getVectorFor(minX+1,  minY),  new Vec(offset.x-1, offset.y));
        float yDot = Vec.dotProduct(getVectorFor(minX, minY+1),  new Vec(offset.x, offset.y-1));
        float maxDot = Vec.dotProduct(getVectorFor(minX+1, minY+1),  new Vec(offset.x-1, offset.y-1));

        float upperDot = minDot * (1-offset.x) + xDot * offset.x;
        float lowerDot = yDot * (1-offset.x) + maxDot * offset.x;

        return upperDot * (1-offset.y) + lowerDot * offset.y;
    }

    public Vec getVectorFor(int x, int y){
        if(memoizedKeys.contains(new Vec(x, y))){
            return memoizedValues.get(memoizedKeys.indexOf(new Vec(x, y)));
        } else {
            memoizedKeys.add(new Vec(x, y));
            memoizedValues.add(new Vec((float)Math.random()*2 -1, (float)Math.random()*2-1));
            return memoizedValues.get(memoizedValues.size()-1);
        }
    }
}
