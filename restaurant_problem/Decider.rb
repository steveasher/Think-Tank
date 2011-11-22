class Resource
  attr_reader :price, :satisfies
  
  def initialize(price, satisfies)
    @price = price
    @satisfies = satisfies
  end
end

class Decider
  def decide(resources, requirements, order = [])
    reqs = requirements.reject{|r| find_solutions(order, r).size > 0}
    return order if reqs.empty?
    
    solutions = find_solutions(resources, reqs.first)
    return nil if solutions.nil?

    cheapOrder = cheapest(valid(solutions.map{|s| decide(resources, tail(reqs), order + [s])}))

    return cheapOrder
  end
  
  def find_solutions(resources, requirement)
    resources.select {|r| !r.satisfies.index(requirement).nil?}
  end
  
  def cheapest(orders)
    orders.sort {|x,y| total(x) <=> total(y)}.first
  end
  
  def valid(orders)
    orders.reject{|o| o.nil? || o.empty?}
  end
  
  def total(order)
    return nil if order.nil? || order.empty?
    order.collect{|o| o.price}.inject{|a,c| a + c}
  end
  
  def tail(array)
    array.last(array.size - 1)
  end
end
