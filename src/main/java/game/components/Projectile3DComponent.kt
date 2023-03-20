package game.components

import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.entity.component.CopyableComponent
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.value.ChangeListener
import javafx.geometry.Point3D

class Projectile3DComponent(direction: Point3D, speed: Double) : Component(), CopyableComponent<Projectile3DComponent> {
    constructor() : this(Point3D(1.0, 0.0, 0.0), 1.0)

    var velocity: Point3D = direction.normalize().multiply(speed)
        private set

    var direction: Point3D
        get() = velocity.normalize()
        set(direction) {
            velocity = direction.normalize().multiply(speed)
            updateRotation()
        }

    private val speedProp = SimpleDoubleProperty(speed)

    private val speedListener = ChangeListener<Number> { _, _, newSpeed ->
        velocity = velocity.normalize().multiply(newSpeed.toDouble())
        updateRotation()
    }

    fun speedProperty(): DoubleProperty = speedProp

    var speed: Double
        get() = speedProp.value
        set(value) {
            speedProp.value = value
        }

    private var isAllowRotation: Boolean = true

    fun allowRotation(allowRotation: Boolean): Projectile3DComponent {
        isAllowRotation = allowRotation
        return this
    }

    private fun updateRotation() {
        if (isAllowRotation)
        //TODO look at Point3D
            velocity
    }

    override fun onAdded() {
        speedProp.addListener(speedListener)
    }

    override fun onUpdate(tpf: Double) {
        //TODO stop when point reached
        entity.translate3D(velocity.multiply(tpf))
    }

    override fun onRemoved() {
        speedProp.removeListener(speedListener)
    }

    override fun copy(): Projectile3DComponent {
        return Projectile3DComponent(direction, speed)
    }

    override fun isComponentInjectionRequired(): Boolean = false
}