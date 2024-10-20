extends TileMapLayer


# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	generateChunk(Vector2i(0, 0))
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	pass

func generateChunk(cPos: Vector2i):
	generateTerrainRectangle(cPos * 64, (cPos + Vector2i(1, 1)) * 64)


func generateTerrainRectangle(beg: Vector2i, end: Vector2i) -> void:
	for i in range(beg.x, end.x):
		for j in range(beg.y, end.y):
			generateTile(Vector2i(i, j))
	
func generateTile(pos: Vector2i) -> void:
	set_cell(pos, 0, Vector2i(2, randi()%2), 0)
