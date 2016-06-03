/**
 */
package no.hal.emfblocks.layout;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see no.hal.emfblocks.layout.LayoutPackage
 * @generated
 */
public interface LayoutFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LayoutFactory eINSTANCE = no.hal.emfblocks.layout.impl.LayoutFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Resource Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Layout</em>'.
	 * @generated
	 */
	ResourceLayout createResourceLayout();

	/**
	 * Returns a new object of class '<em>Work Area Representation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Work Area Representation</em>'.
	 * @generated
	 */
	WorkAreaRepresentation createWorkAreaRepresentation();

	/**
	 * Returns a new object of class '<em>Block Representation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Representation</em>'.
	 * @generated
	 */
	BlockRepresentation createBlockRepresentation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LayoutPackage getLayoutPackage();

} //EmfjigsawlayoutFactory
