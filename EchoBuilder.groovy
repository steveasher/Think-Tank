package com.zetassociates.web.rad;

import groovy.util.BuilderSupport
import org.karora.cooee.app.*
import org.karora.cooee.ng.*

/**
* Builder for Echo 2 component hierarchies
*/
class EchoBuilder extends BuilderSupport {
	protected Map creatorMap = [ 
									'accordionPane' : { new AccordionPane() },
									'borderPane' : { new BorderPane() },
									'button' : { new Button() },
									'calendarSelect' : { new CalendarSelect() },
									'checkBox' : { new CheckBox() },
									'colorSelect' : { new ColorSelect() },
									'column' : { new Column() },
									'contentPane' : { new ContentPane() },
									'dragSource' : { new DragSource() },
									'dropDownMenu' : { new DropDownMenu() },
									'grid' : { new Grid() },
									'label' : { it == null ? new Label() : new Label(it) },
									'listBox' : { new ListBox() },
									'menuBarPane' : { new MenuBarPane() },
									'panel' : { new Panel() },
									'passwordField' : { new PasswordField() },
									'pushButton' : { new PushButton() },
									'radioButton' : { new RadioButton() },
									'row' : { new Row() },
									'selectField' : { new SelectField() },
									'splitPane' : { new SplitPane() },
									'strut' : { new Strut() },
									'table' : { new Table() },
									'tabPane' : { new TabPane() },
									'textArea' : { new TextArea() },
									'textField' : { new TextField() },
									'transitionPane' : { new TransitionPane() },
									'window' : { new Window() },
									'windowPane' : { new WindowPane() }
	                           ]					
	
	private static final List extentAttributes = [ 'height', 'width', 'separatorPosition', 'separatorWidth', 'separatorHeight' ]
	
	def createNode(name) {
		creatorMap[name].call()
	}
	
	def createNode(name, value) {
		creatorMap[name].call(value)
	}
	
	def createNode(name, Map attributes) {
		def node = createNode(name)
		this.setAttributes(node, attributes)
		
		node
	}
	
	def createNode(name, Map attributes, value) {
		def node = createNode(name, value)
		this.setAttributes(node, attributes)
		
		node
	}
	
	private void setAttributes(node, Map attributes) {
		attributes.each { key, value ->  
			
			if (extentAttributes.contains(key)) {
				value = new Extent(value, Extent.PX)
			}
			
			node[key] = value 
		}
	}
	
	void setParent(parent, child) {
		parent.add(child)
	}
	
	void nodeCompleted(parent, node) {
		
	}

}