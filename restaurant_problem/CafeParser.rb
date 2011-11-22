require 'Decider'

class Cafe
  attr_accessor :id, :menuItems
  
  def initialize(id, menuItems)
    @id = id
    @menuItems = menuItems
  end
  
  def place_order(items)
    decider = Decider.new
    decider.total(decider.decide(@menuItems, items))
  end
end

class CafeParser
  def initialize 
    @cafeMap = Hash.new
  end

  def cafes
    @cafeMap.values
  end
  
  def parse(line)
    cells = line.split(',')
    cafeId = cells.shift.strip.to_i
    price = cells.shift.strip.to_f
    menuItem = Resource.new(price, cells.map{|c| c.strip})

    if @cafeMap[cafeId] == nil
      @cafeMap[cafeId] = Cafe.new(cafeId, [menuItem])
    else
      @cafeMap[cafeId].menuItems << menuItem
    end
  end
end