class_name EscMenu
extends Control

@onready var resume_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/ResumeButton as Button
@onready var exit_button: Button = $MarginContainer/HBoxContainer/VBoxContainer/ExitButton as Button
@onready var world = $"../../.."
@onready var main_menu = $"../../../../Main_Menu"

@onready var camera = $".."
@onready var main_menu_camera = $"../../../../Main_Menu/Camera2D"

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
	main_menu_camera.make_current()
	main_menu.visible = true
	world.visible = false
