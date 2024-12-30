extends Node2D

@onready var esc_menu = $Metallurgist/Camera2D/EscMenu
var paused = false;

var seed = 0
# Called when the node enters the scene tree for the first time.
func _ready() -> void: # Replace with function body.
	visible = false;

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	if Input.is_action_just_pressed("escape"):
		pauseMenu()

func getSeed() -> int:
	return seed

func pauseMenu():
	if!paused:
		esc_menu.show()
		Engine.time_scale = 0
	else:
		esc_menu.hide()	
		Engine.time_scale = 1
	
	paused = !paused;	
