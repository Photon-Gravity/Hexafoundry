class_name MainMenu
extends Control

@onready var continue_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/Button
@onready var exit_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/Button5
@onready var debug_world = preload("res://scenes/World/world.tscn")

# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	continue_button.button_down.connect(continue_button_down)
	exit_button.button_down.connect(exit_button_down)


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	pass

func continue_button_down() -> void:
	get_tree().change_scene_to_packed(debug_world)

func exit_button_down() -> void:
	get_tree().quit()
