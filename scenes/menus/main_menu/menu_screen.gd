class_name MainMenu
extends Control

@onready var continue_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/Button
@onready var exit_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/Button5

@onready var world = $"../World"

@onready var camera = $"Camera2D"
@onready var world_camera = $"../World/Metallurgist/Camera2D"

# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	handle_connecting_signals()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	pass

func continue_button_down() -> void:
	world_camera.make_current()
	world.visible = true;
	visible = false;
	

func exit_button_down() -> void:
	get_tree().quit()
	
func handle_connecting_signals()->void:
	continue_button.button_down.connect(continue_button_down)
	exit_button.button_down.connect(exit_button_down)
