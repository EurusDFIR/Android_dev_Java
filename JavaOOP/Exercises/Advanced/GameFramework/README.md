# Bài tập: Game Development Framework

## Mục tiêu:

Xây dựng framework cho game development với focus vào OOP design patterns

## Yêu cầu chi tiết:

### 1. Abstract Class: GameObject

- Fields: `id`, `position` (Point2D), `velocity`, `isActive`, `layer`
- Abstract method: `update(long deltaTime)`
- Abstract method: `render(Graphics2D graphics)`
- Abstract method: `onCollision(GameObject other)`
- Concrete method: `move(Point2D direction)`
- Concrete method: `setActive(boolean active)`
- Implement Comparable để sort theo layer

### 2. Interface: Collidable

- Method: `getBoundingBox()` - return Rectangle
- Method: `checkCollision(Collidable other)`
- Method: `getCollisionMask()` - return bitmask for collision layers

### 3. Interface: Updatable

- Method: `update(long deltaTime)`
- Method: `pause()`
- Method: `resume()`
- Method: `isUpdateEnabled()`

### 4. Interface: Drawable

- Method: `render(Graphics2D graphics)`
- Method: `setVisible(boolean visible)`
- Method: `getZOrder()` - for rendering order
- Method: `getBounds()`

### 5. Game Entity Classes:

#### Player extends GameObject implements Collidable, Controllable

- Fields: `health`, `maxHealth`, `score`, `lives`, `powerUps`
- Fields: `inputHandler`, `movementSpeed`, `jumpForce`
- Method: `takeDamage(int damage)`
- Method: `heal(int amount)`
- Method: `addScore(int points)`
- Method: `collectPowerUp(PowerUp powerUp)`
- Implement input handling cho movement

#### Enemy extends GameObject implements Collidable, AIControlled

- Fields: `enemyType`, `aggroRange`, `attackDamage`, `patrolPath`
- Fields: `currentTarget`, `aiState` (PATROL, CHASE, ATTACK, RETREAT)
- Method: `calculateNextMove(Player player)`
- Method: `attack(GameObject target)`
- Method: `checkAggro(Player player)`
- Implement basic AI behaviors

#### PowerUp extends GameObject implements Collidable, Consumable

- Fields: `powerUpType`, `duration`, `effectStrength`, `isTemporary`
- Method: `applyEffect(Player player)`
- Method: `removeEffect(Player player)`
- Different types: SPEED_BOOST, HEALTH_PACK, EXTRA_LIFE, SHIELD

#### Projectile extends GameObject implements Collidable

- Fields: `damage`, `owner`, `maxDistance`, `travelledDistance`
- Method: `checkMaxDistance()`
- Auto-destroy khi hit target hoặc max distance

### 6. Interface: Controllable

- Method: `handleInput(InputEvent event)`
- Method: `setInputHandler(InputHandler handler)`
- Method: `getAvailableActions()`

### 7. Interface: AIControlled

- Method: `updateAI(long deltaTime, GameState gameState)`
- Method: `setAIBehavior(AIBehavior behavior)`
- Method: `getAIState()`

### 8. Enum Classes:

```java
public enum GameState {
    MENU, PLAYING, PAUSED, GAME_OVER, LOADING
}

public enum PowerUpType {
    SPEED_BOOST(Duration.ofSeconds(10), 1.5f),
    HEALTH_PACK(Duration.ZERO, 25),
    EXTRA_LIFE(Duration.ZERO, 1),
    SHIELD(Duration.ofSeconds(15), 1);
}

public enum EnemyType {
    ZOMBIE(100, 15, 2.0f),
    SKELETON(75, 20, 2.5f),
    BOSS(500, 50, 1.5f);
}
```

### 9. Class: GameEngine (Singleton)

- Fields: `gameObjects`, `gameState`, `inputManager`, `renderer`
- Method: `addGameObject(GameObject obj)`
- Method: `removeGameObject(String id)`
- Method: `update(long deltaTime)` - update tất cả objects
- Method: `render(Graphics2D graphics)` - render theo z-order
- Method: `checkCollisions()` - efficient collision detection
- Method: `handleInput(InputEvent event)`

### 10. Class: SceneManager

- Fields: `currentScene`, `scenes` (Map), `transitionInProgress`
- Method: `loadScene(String sceneName)`
- Method: `addScene(String name, Scene scene)`
- Method: `transitionToScene(String sceneName, Transition transition)`
- Support cho scene transitions với effects

### 11. Class: ResourceManager (Singleton)

- Method: `loadTexture(String path)`
- Method: `loadSound(String path)`
- Method: `preloadResources(List<String> resourcePaths)`
- Method: `unloadResource(String resourceId)`
- Implement resource caching và memory management

### 12. Design Patterns Implementation:

#### Factory Pattern: GameObjectFactory

```java
public static GameObject createGameObject(GameObjectType type, Point2D position, Map<String, Object> parameters)
```

#### Observer Pattern: GameEvents

- EventListener interface cho game events
- GameEventManager để manage listeners
- Events: PLAYER_DAMAGED, ENEMY_DEFEATED, POWERUP_COLLECTED, LEVEL_COMPLETE

#### Command Pattern: InputCommands

- Command interface với execute() method
- MoveCommand, AttackCommand, JumpCommand classes
- InputHandler để map keys to commands

#### State Pattern: AIBehavior

- AIState interface với different implementations
- PatrolState, ChaseState, AttackState, RetreatState
- Context switching based on game conditions

### 13. Advanced Features:

#### Physics System

- Class: PhysicsEngine
- Method: `applyGravity(GameObject obj)`
- Method: `resolveCollision(Collidable obj1, Collidable obj2)`
- Method: `updatePhysics(long deltaTime)`

#### Animation System

- Class: Animation với keyframes
- Method: `addKeyFrame(long time, Object value)`
- Method: `getCurrentValue(long currentTime)`
- Support cho position, rotation, scale animations

#### Sound System

- Class: AudioManager
- Method: `playSound(String soundId, float volume)`
- Method: `playMusic(String musicId, boolean loop)`
- Method: `setMasterVolume(float volume)`

### 14. Testing Requirements:

- Unit tests cho collision detection
- Performance tests cho large numbers of objects
- AI behavior testing với different scenarios
- Memory leak testing cho resource management
- Input handling tests

### 15. Performance Optimizations:

- Object pooling cho frequently created objects (bullets, particles)
- Spatial partitioning cho collision detection
- Level-of-detail cho distant objects
- Efficient rendering với frustum culling

## Technical Requirements:

- Sử dụng generics cho type safety
- Thread-safe implementations cho core systems
- Proper encapsulation với clear public APIs
- Extensive use của composition over inheritance
- Clean separation of concerns

## Đánh giá:

- Design patterns implementation (25%)
- Performance optimization (20%)
- Code architecture (20%)
- OOP principles application (20%)
- Testing coverage (15%)
