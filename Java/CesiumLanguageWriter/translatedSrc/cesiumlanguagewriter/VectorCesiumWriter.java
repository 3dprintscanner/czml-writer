package cesiumlanguagewriter;


import agi.foundation.compatibility.*;
import agi.foundation.compatibility.DisposeHelper;
import agi.foundation.compatibility.Func1;
import agi.foundation.compatibility.Lazy;
import cesiumlanguagewriter.advanced.*;
import cesiumlanguagewriter.BooleanCesiumWriter;
import cesiumlanguagewriter.ColorCesiumWriter;
import cesiumlanguagewriter.DirectionCesiumWriter;
import cesiumlanguagewriter.DoubleCesiumWriter;
import java.awt.Color;
import java.util.List;

/**
 *  
 Writes a <code>Vector</code> to a  {@link CesiumOutputStream}.  A <code>Vector</code> defines a graphical vector that originates at the `position` property and extends in the provided direction for the provided length.
 

 */
public class VectorCesiumWriter extends CesiumPropertyWriter<VectorCesiumWriter> {
	/**
	 *  
	The name of the <code>show</code> property.
	

	 */
	public static final String ShowPropertyName = "show";
	/**
	 *  
	The name of the <code>color</code> property.
	

	 */
	public static final String ColorPropertyName = "color";
	/**
	 *  
	The name of the <code>direction</code> property.
	

	 */
	public static final String DirectionPropertyName = "direction";
	/**
	 *  
	The name of the <code>length</code> property.
	

	 */
	public static final String LengthPropertyName = "length";
	/**
	 *  
	The name of the <code>minimumLengthInPixels</code> property.
	

	 */
	public static final String MinimumLengthInPixelsPropertyName = "minimumLengthInPixels";
	private Lazy<BooleanCesiumWriter> m_show = new Lazy<cesiumlanguagewriter.BooleanCesiumWriter>(new Func1<cesiumlanguagewriter.BooleanCesiumWriter>() {
		public cesiumlanguagewriter.BooleanCesiumWriter invoke() {
			return new BooleanCesiumWriter(ShowPropertyName);
		}
	}, false);
	private Lazy<ColorCesiumWriter> m_color = new Lazy<cesiumlanguagewriter.ColorCesiumWriter>(new Func1<cesiumlanguagewriter.ColorCesiumWriter>() {
		public cesiumlanguagewriter.ColorCesiumWriter invoke() {
			return new ColorCesiumWriter(ColorPropertyName);
		}
	}, false);
	private Lazy<DirectionCesiumWriter> m_direction = new Lazy<cesiumlanguagewriter.DirectionCesiumWriter>(new Func1<cesiumlanguagewriter.DirectionCesiumWriter>() {
		public cesiumlanguagewriter.DirectionCesiumWriter invoke() {
			return new DirectionCesiumWriter(DirectionPropertyName);
		}
	}, false);
	private Lazy<DoubleCesiumWriter> m_length = new Lazy<cesiumlanguagewriter.DoubleCesiumWriter>(new Func1<cesiumlanguagewriter.DoubleCesiumWriter>() {
		public cesiumlanguagewriter.DoubleCesiumWriter invoke() {
			return new DoubleCesiumWriter(LengthPropertyName);
		}
	}, false);
	private Lazy<DoubleCesiumWriter> m_minimumLengthInPixels = new Lazy<cesiumlanguagewriter.DoubleCesiumWriter>(new Func1<cesiumlanguagewriter.DoubleCesiumWriter>() {
		public cesiumlanguagewriter.DoubleCesiumWriter invoke() {
			return new DoubleCesiumWriter(MinimumLengthInPixelsPropertyName);
		}
	}, false);

	/**
	 *  
	Initializes a new instance.
	

	 */
	public VectorCesiumWriter(String propertyName) {
		super(propertyName);
	}

	/**
	 *  
	Initializes a new instance as a copy of an existing instance.
	
	

	 * @param existingInstance The existing instance to copy.
	 */
	protected VectorCesiumWriter(VectorCesiumWriter existingInstance) {
		super(existingInstance);
	}

	@Override
	public VectorCesiumWriter clone() {
		return new VectorCesiumWriter(this);
	}

	/**
	 *  Gets the writer for the <code>show</code> property.  The returned instance must be opened by calling the  {@link CesiumElementWriter#open} method before it can be used for writing.  The <code>show</code> property defines whether or not the vector is shown.
	

	 */
	public final BooleanCesiumWriter getShowWriter() {
		return m_show.getValue();
	}

	/**
	 *  
	Opens and returns the writer for the <code>show</code> property.  The <code>show</code> property defines whether or not the vector is shown.
	

	 */
	public final BooleanCesiumWriter openShowProperty() {
		openIntervalIfNecessary();
		return this.<BooleanCesiumWriter> openAndReturn(getShowWriter());
	}

	/**
	 *  
	Writes a value for the <code>show</code> property as a <code>boolean</code> value.  The <code>show</code> property specifies whether or not the vector is shown.
	
	

	 * @param value The value.
	 */
	public final void writeShowProperty(boolean value) {
		{
			cesiumlanguagewriter.BooleanCesiumWriter writer = openShowProperty();
			try {
				writer.writeBoolean(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>show</code> property as a <code>reference</code> value.  The <code>show</code> property specifies whether or not the vector is shown.
	
	

	 * @param value The reference.
	 */
	public final void writeShowPropertyReference(Reference value) {
		{
			cesiumlanguagewriter.BooleanCesiumWriter writer = openShowProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>show</code> property as a <code>reference</code> value.  The <code>show</code> property specifies whether or not the vector is shown.
	
	

	 * @param value The earliest date of the interval.
	 */
	public final void writeShowPropertyReference(String value) {
		{
			cesiumlanguagewriter.BooleanCesiumWriter writer = openShowProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>show</code> property as a <code>reference</code> value.  The <code>show</code> property specifies whether or not the vector is shown.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyName The property on the referenced object.
	 */
	public final void writeShowPropertyReference(String identifier, String propertyName) {
		{
			cesiumlanguagewriter.BooleanCesiumWriter writer = openShowProperty();
			try {
				writer.writeReference(identifier, propertyName);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>show</code> property as a <code>reference</code> value.  The <code>show</code> property specifies whether or not the vector is shown.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyNames The hierarchy of properties to be indexed on the referenced object.
	 */
	public final void writeShowPropertyReference(String identifier, String[] propertyNames) {
		{
			cesiumlanguagewriter.BooleanCesiumWriter writer = openShowProperty();
			try {
				writer.writeReference(identifier, propertyNames);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  Gets the writer for the <code>color</code> property.  The returned instance must be opened by calling the  {@link CesiumElementWriter#open} method before it can be used for writing.  The <code>color</code> property defines the color of the vector.
	

	 */
	public final ColorCesiumWriter getColorWriter() {
		return m_color.getValue();
	}

	/**
	 *  
	Opens and returns the writer for the <code>color</code> property.  The <code>color</code> property defines the color of the vector.
	

	 */
	public final ColorCesiumWriter openColorProperty() {
		openIntervalIfNecessary();
		return this.<ColorCesiumWriter> openAndReturn(getColorWriter());
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>rgba</code> value.  The <code>color</code> property specifies the color of the vector.
	
	

	 * @param color The color.
	 */
	public final void writeColorProperty(Color color) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeRgba(color);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>rgba</code> value.  The <code>color</code> property specifies the color of the vector.
	
	
	
	
	

	 * @param red The red component in the range 0 to 255.
	 * @param green The green component in the range 0 to 255.
	 * @param blue The blue component in the range 0 to 255.
	 * @param alpha The alpha component in the range 0 to 255.
	 */
	public final void writeColorProperty(int red, int green, int blue, int alpha) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeRgba(red, green, blue, alpha);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>rgba</code> value.  The <code>color</code> property specifies the color of the vector.
	
	
	
	
	

	 * @param dates The dates at which the value is specified.
	 * @param colors The color corresponding to each date.
	 * @param startIndex The index of the first element to use in the `colors` collection.
	 * @param length The number of elements to use from the `colors` collection.
	 */
	public final void writeColorProperty(List<JulianDate> dates, List<Color> colors, int startIndex, int length) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeRgba(dates, colors, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>rgbaf</code> value.  The <code>color</code> property specifies the color of the vector.
	
	
	
	
	

	 * @param red The red component in the range 0 to 1.0.
	 * @param green The green component in the range 0 to 1.0.
	 * @param blue The blue component in the range 0 to 1.0.
	 * @param alpha The alpha component in the range 0 to 1.0.
	 */
	public final void writeColorPropertyRgbaf(float red, float green, float blue, float alpha) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeRgbaf(red, green, blue, alpha);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>reference</code> value.  The <code>color</code> property specifies the color of the vector.
	
	

	 * @param value The reference.
	 */
	public final void writeColorPropertyReference(Reference value) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>reference</code> value.  The <code>color</code> property specifies the color of the vector.
	
	

	 * @param value The earliest date of the interval.
	 */
	public final void writeColorPropertyReference(String value) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>reference</code> value.  The <code>color</code> property specifies the color of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyName The property on the referenced object.
	 */
	public final void writeColorPropertyReference(String identifier, String propertyName) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeReference(identifier, propertyName);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>color</code> property as a <code>reference</code> value.  The <code>color</code> property specifies the color of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyNames The hierarchy of properties to be indexed on the referenced object.
	 */
	public final void writeColorPropertyReference(String identifier, String[] propertyNames) {
		{
			cesiumlanguagewriter.ColorCesiumWriter writer = openColorProperty();
			try {
				writer.writeReference(identifier, propertyNames);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  Gets the writer for the <code>direction</code> property.  The returned instance must be opened by calling the  {@link CesiumElementWriter#open} method before it can be used for writing.  The <code>direction</code> property defines the direction of the vector.
	

	 */
	public final DirectionCesiumWriter getDirectionWriter() {
		return m_direction.getValue();
	}

	/**
	 *  
	Opens and returns the writer for the <code>direction</code> property.  The <code>direction</code> property defines the direction of the vector.
	

	 */
	public final DirectionCesiumWriter openDirectionProperty() {
		openIntervalIfNecessary();
		return this.<DirectionCesiumWriter> openAndReturn(getDirectionWriter());
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>spherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The value.
	 */
	public final void writeDirectionProperty(Spherical value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeSpherical(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>spherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 */
	public final void writeDirectionProperty(List<JulianDate> dates, List<Spherical> values) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeSpherical(dates, values);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>spherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeDirectionProperty(List<JulianDate> dates, List<Spherical> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeSpherical(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitSpherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The value.
	 */
	public final void writeDirectionPropertyUnitSpherical(UnitSpherical value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitSpherical(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitSpherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 */
	public final void writeDirectionPropertyUnitSpherical(List<JulianDate> dates, List<UnitSpherical> values) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitSpherical(dates, values);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitSpherical</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeDirectionPropertyUnitSpherical(List<JulianDate> dates, List<UnitSpherical> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitSpherical(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>cartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The value.
	 */
	public final void writeDirectionPropertyCartesian(Cartesian value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeCartesian(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>cartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 */
	public final void writeDirectionPropertyCartesian(List<JulianDate> dates, List<Cartesian> values) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeCartesian(dates, values);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>cartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeDirectionPropertyCartesian(List<JulianDate> dates, List<Cartesian> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeCartesian(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitCartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The value.
	 */
	public final void writeDirectionPropertyUnitCartesian(UnitCartesian value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitCartesian(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitCartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 */
	public final void writeDirectionPropertyUnitCartesian(List<JulianDate> dates, List<UnitCartesian> values) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitCartesian(dates, values);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>unitCartesian</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	
	
	

	 * @param dates The dates at which the vector is specified.
	 * @param values The values corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeDirectionPropertyUnitCartesian(List<JulianDate> dates, List<UnitCartesian> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeUnitCartesian(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>reference</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The reference.
	 */
	public final void writeDirectionPropertyReference(Reference value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>reference</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	

	 * @param value The earliest date of the interval.
	 */
	public final void writeDirectionPropertyReference(String value) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>reference</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyName The property on the referenced object.
	 */
	public final void writeDirectionPropertyReference(String identifier, String propertyName) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeReference(identifier, propertyName);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>direction</code> property as a <code>reference</code> value.  The <code>direction</code> property specifies the direction of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyNames The hierarchy of properties to be indexed on the referenced object.
	 */
	public final void writeDirectionPropertyReference(String identifier, String[] propertyNames) {
		{
			cesiumlanguagewriter.DirectionCesiumWriter writer = openDirectionProperty();
			try {
				writer.writeReference(identifier, propertyNames);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  Gets the writer for the <code>length</code> property.  The returned instance must be opened by calling the  {@link CesiumElementWriter#open} method before it can be used for writing.  The <code>length</code> property defines the graphical length of the vector.
	

	 */
	public final DoubleCesiumWriter getLengthWriter() {
		return m_length.getValue();
	}

	/**
	 *  
	Opens and returns the writer for the <code>length</code> property.  The <code>length</code> property defines the graphical length of the vector.
	

	 */
	public final DoubleCesiumWriter openLengthProperty() {
		openIntervalIfNecessary();
		return this.<DoubleCesiumWriter> openAndReturn(getLengthWriter());
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>number</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	

	 * @param value The value.
	 */
	public final void writeLengthProperty(double value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeNumber(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>number</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	
	
	
	

	 * @param dates The dates at which the value is specified.
	 * @param values The value corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeLengthProperty(List<JulianDate> dates, List<Double> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeNumber(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>reference</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	

	 * @param value The reference.
	 */
	public final void writeLengthPropertyReference(Reference value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>reference</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	

	 * @param value The earliest date of the interval.
	 */
	public final void writeLengthPropertyReference(String value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>reference</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyName The property on the referenced object.
	 */
	public final void writeLengthPropertyReference(String identifier, String propertyName) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeReference(identifier, propertyName);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>length</code> property as a <code>reference</code> value.  The <code>length</code> property specifies the graphical length of the vector.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyNames The hierarchy of properties to be indexed on the referenced object.
	 */
	public final void writeLengthPropertyReference(String identifier, String[] propertyNames) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openLengthProperty();
			try {
				writer.writeReference(identifier, propertyNames);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  Gets the writer for the <code>minimumLengthInPixels</code> property.  The returned instance must be opened by calling the  {@link CesiumElementWriter#open} method before it can be used for writing.  The <code>minimumLengthInPixels</code> property defines the minimum graphical length of the vector in pixels.
	

	 */
	public final DoubleCesiumWriter getMinimumLengthInPixelsWriter() {
		return m_minimumLengthInPixels.getValue();
	}

	/**
	 *  
	Opens and returns the writer for the <code>minimumLengthInPixels</code> property.  The <code>minimumLengthInPixels</code> property defines the minimum graphical length of the vector in pixels.
	

	 */
	public final DoubleCesiumWriter openMinimumLengthInPixelsProperty() {
		openIntervalIfNecessary();
		return this.<DoubleCesiumWriter> openAndReturn(getMinimumLengthInPixelsWriter());
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>number</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	

	 * @param value The value.
	 */
	public final void writeMinimumLengthInPixelsProperty(double value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeNumber(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>number</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	
	
	
	

	 * @param dates The dates at which the value is specified.
	 * @param values The value corresponding to each date.
	 * @param startIndex The index of the first element to use in the `values` collection.
	 * @param length The number of elements to use from the `values` collection.
	 */
	public final void writeMinimumLengthInPixelsProperty(List<JulianDate> dates, List<Double> values, int startIndex, int length) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeNumber(dates, values, startIndex, length);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>reference</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	

	 * @param value The reference.
	 */
	public final void writeMinimumLengthInPixelsPropertyReference(Reference value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>reference</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	

	 * @param value The earliest date of the interval.
	 */
	public final void writeMinimumLengthInPixelsPropertyReference(String value) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeReference(value);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>reference</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyName The property on the referenced object.
	 */
	public final void writeMinimumLengthInPixelsPropertyReference(String identifier, String propertyName) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeReference(identifier, propertyName);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}

	/**
	 *  
	Writes a value for the <code>minimumLengthInPixels</code> property as a <code>reference</code> value.  The <code>minimumLengthInPixels</code> property specifies the minimum graphical length of the vector in pixels.
	
	
	

	 * @param identifier The identifier of the object which contains the referenced property.
	 * @param propertyNames The hierarchy of properties to be indexed on the referenced object.
	 */
	public final void writeMinimumLengthInPixelsPropertyReference(String identifier, String[] propertyNames) {
		{
			cesiumlanguagewriter.DoubleCesiumWriter writer = openMinimumLengthInPixelsProperty();
			try {
				writer.writeReference(identifier, propertyNames);
			} finally {
				DisposeHelper.dispose(writer);
			}
		}
	}
}