package core.entity.unit;

public class MoveForwardsAI extends UnitAI{
	@Override
	public void processUnit(Unit u) {
		float dx = (float)Math.cos(u.rotation) * u.type.speed;
		float dy = (float)Math.sin(u.rotation) * u.type.speed;

		u.x += dx;
		u.y += dy;
		u.rotation += (float)Math.PI*2/60f/2;
	}
}
