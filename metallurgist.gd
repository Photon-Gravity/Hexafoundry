extends CharacterBody2D


const SPEED = 125.0
const JUMP_VELOCITY = -400.0
const INV_SQRT_2 = 1/sqrt(2)

@onready var animated_sprite = $AnimatedSprite2D

func _physics_process(delta: float) -> void:
	# Get the input direction and handle the movement/deceleration.
	# As good practice, you should replace UI actions with custom gameplay actions.
	var directionX := Input.get_axis("move_left", "move_right")
	var directionY := Input.get_axis("move_up", "move_down")
	
	if directionX:
		velocity.x = directionX * SPEED
	else:
		velocity.x = move_toward(velocity.x, 0, SPEED)

	if directionY:
		velocity.y = directionY * SPEED
	else:
		velocity.y = move_toward(velocity.y, 0, SPEED)	

	if directionX && directionY:
		velocity.x *= INV_SQRT_2;
		velocity.y *= INV_SQRT_2;
	
	if(Engine.time_scale > 0):
		if directionX > 0:
			animated_sprite.flip_h = false
		elif directionX < 0:
			animated_sprite.flip_h = true;
		
		#animations
		if directionX == 0 && directionY == 0:
			animated_sprite.play("idle")
		else:
			animated_sprite.play("run")	
	
	move_and_slide()
