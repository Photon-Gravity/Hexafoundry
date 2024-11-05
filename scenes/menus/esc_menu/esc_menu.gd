class_name EscMenu
extends Control

@onready var resume_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/ResumeButton as Button
@onready var exit_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/ExitButton as Button
@onready var world = $"../../.."
const MENU_SCREEN = preload("res://scenes/menus/main_menu/menu_screen.tscn") as PackedScene

# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	resume_button.button_down.connect(resume_button_down)
	exit_button.button_down.connect(exit_button_down)
	hide()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	pass

func resume_button_down() -> void:
	world.pauseMenu()

func exit_button_down() -> void:
	get_tree().change_scene_to_packed(MENU_SCREEN)
