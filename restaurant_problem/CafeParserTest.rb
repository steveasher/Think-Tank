require 'test/unit'
require 'CafeParser'

class CafeParserTest < Test::Unit::TestCase
  
  def test_parse
    @parser.parse('1, 4.00, burger')
    @parser.parse('1, 5.00, fries')
    @parser.parse('6, 6.00, extreme_fajita, jalapeno_poppers, extra_salsa')
    
    cafes = @parser.cafes
    assert_equal 2, cafes.size
    assert !cafes.select {|c| c.id == 6 && 
                            c.menuItems.size == 1 && 
                            c.menuItems[0].satisfies.include?('extra_salsa')}.empty?
    assert !cafes.select {|c| c.id == 1 && c.menuItems.size == 2}.empty?
  end
  
  def setup
    @parser = CafeParser.new
  end
end