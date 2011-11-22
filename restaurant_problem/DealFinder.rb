#!/usr/bin/ruby -w

require 'CafeParser'

parser = CafeParser.new

IO.foreach(ARGV.shift) { |l| parser.parse(l) }

results = {}
parser.cafes.each do |c|
  option = c.place_order(ARGV)
  results[c.id] = option if not option.nil?
end

deal = results.sort{|x,y| x[1] <=> y[1]}.first

print(deal[0].to_s + ', ' + deal[1].to_s + "\n")

