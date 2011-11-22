require 'test/unit'
require 'Decider'

class DeciderTest < Test::Unit::TestCase
  
  def test_total
    assert_equal 6, @finder.total([@r1, @r2, @r3])
    assert_equal 5, @finder.total([@r2, @r3])
  end
  
  def test_total_for_empty_and_null
    assert_equal nil, @finder.total([])
    assert_equal nil, @finder.total(nil)
  end
  
  def test_cheapest
    order1 = [@r1, @r2, @r3]
    cheapest = @finder.cheapest([order1, [@r4, @r5, @r6], [@r7]])
    
    assert_equal 3, cheapest.size
    assert_equal order1, cheapest
  end
  
  def test_just_thirsty
    order = @finder.decide([@r1], ['water'])
    
    assert_equal 1, @finder.total(order)
  end
  
  def test_two_requirements
    order = @finder.decide(@allResources, ['water', 'tofu'])
    
    assert_equal 2, order.size
    assert_equal 3, @finder.total(order)
  end
  
  def test_take_value_meal
    order = @finder.decide(@allResources, ['burger', 'fries'])

    assert_same @r7, order[0]
  end
  
  def test_value_meal_not_a_deal
    order = @finder.decide(@allResources, ['salad', 'tofu'])

    assert_equal 2, order.size
    assert_equal 6, @finder.total(order)
  end
  
  def test_value_meal_over_satisfies
    order = @finder.decide(@allResources, ['fries', 'burger', 'salad'])

    assert_equal 1, order.size
    assert_equal 10, @finder.total(order)
  end
  
  def test_you_cant_buy_love
    assert_equal nil, @finder.decide(@allResources, ['burger', 'fries', 'love'])
  end
  
  def test_do_not_alter_in_place
    reqs = ['water', 'tofu']
    order = [@r8, @r9]
    @finder.decide(@allResources, reqs.freeze, order.freeze)
  end
  
  def setup
    @finder = Decider.new()
    @allResources = [@r1 = Resource.new(1, ['water']),
                    @r2 = Resource.new(2, ['tofu']),
                    @r3 = Resource.new(3, ['fries']),
                    @r4 = Resource.new(4, ['salad']),
                    @r5 = Resource.new(5, ['burger']),
                    @r6 = Resource.new(6, ['wine']),
                    @r7 = Resource.new(7, ['burger', 'fries']),
                    @r8 = Resource.new(8, ['tofu', 'salad']),
                    @r9 = Resource.new(9, ['burger', 'fries', 'wine']),
                    @r10 = Resource.new(10, ['tofu', 'fries', 'salad', 'burger'])]
                    
    @allResources.freeze
  end
end