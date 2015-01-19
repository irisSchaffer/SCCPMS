Areen Said & Iris Schaffer


# Log Book

## Preamble
GitHub is a far better log book than we are, which is why there are no lengthy explanations or descriptions for the steps of the implementations. For details, please refer to our GitHub repository: [https://github.com/irisSchaffer/SCCPMS](https://github.com/irisSchaffer/SCCPMS).

## Logs

###### 08.12.2014

Skype conversation to brainstorm, discuss ideas and write project proposal

### Project Proposal Key Words:

- Santa Claus christmas present management system
- Children —> write wishlists and receive presents, prepare cookies & milk for Santa / good child / bad child?
- Santa Claus HQ —> gets wishlist (observer?), lets elfs create gifts and wrap gifts
- Gifts know how they are fabricated
- Santa Claus delivers presents

### Project Proposal:

We want to create a Santa Claus christmas present management system (SCCPMS). Children should be able to write letters to Santa and, if they were good children (and/or put cookies and milk out), get presents.
Santa receives the letters, produces the presents, packs them in gift wrap of different colour and pattern and in the end delivers them to the children’s houses.

---

###### 16.01.2015

We are panicking because a colleague pointed out that the project deadline is the 19th of January.

---

###### 17.01.2015

### Planning

*11:00-14:00*: Identification of all parts of the application and creation handwritten class diagrams:

- Child
	- Address
	- Way to determine how good a child was
	- Wishlist

- Santa HQ
	- Knows children
		- Child Record
			- Child
			- Christmas Record (year, wishlist, etc.)
	- Way of getting wishlist (—> event, when child finishes wishlist?)
	- Gifts
		- Gift Wrap
		- Product
			- Product Factory creates Products
	- Gift Factory
		- Knows product factories, produces product and wraps it in gift wrap

### Creation of git repository, Eclipse configurations etc.
*14:00-14:30*: Because all team members already have a github account, we decided to go with github to host our repo: [https://github.com/irisSchaffer/SCCPMS](https://github.com/irisSchaffer/SCCPMS)

### Implementation
*15:14*: Started writing tests for gift factory.

*16:31*: Implemented Gift Factory according to unit test.

*17:23*: Tests for child record, christmas record and all child methods needed for child record.

*18:15*: Added Child, ChildRecord and ChristmasRecord according to unit tests.

*18:46*: Added in new Products and Factories.

##### *Taking a break.* 

---

###### 18.01.2014

*02:27*: Added unit tests for Wish Granter and started implementing Wish Granter and observer pattern for child

##### *Going to bed*

*18:05:* Analysing late night changes and planning refactoring:

- Wish Granter name changed to Santa Claus (who implements the interface WishGranterInterface), so that parents or the Christkindl could be other
possible WishGranters for the child.
- Removed possibility of child to have more than one wish granter.
- Got all unit tests to run again, will now adapt unit tests to new
requirements.

*18:17:* Wrote new unit tests for adapted Santa Claus.

*18:18:* Moved gift related stuff to its own package

*18:12:* Added GiftGivingStrategy test, interface and concrete implementation

*19:27:* Added unit tests for Santa Claus' deliver gifts method.

*20:57* Implemented state pattern for child's wish list.

*21:12* Implemented deliverGifts() method for Santa Claus, adjusted unit tests and implemented the following features:

- Added child to gift, because it is a requirement for a gift to be for
someone.
- Added possibility for children to receive gifts.
- Added goodness of child to christmas record for comparison / data
analyses etc.
- Added toString methods and console output.

*23:15* Removed State Pattern again:

- First implemented state pattern properly (with `addToWishList()`, `putWishListOnWindowSill()` and `takeWishList()` as methods on states)
- Discussed how much of a code smell it is to leave creation of states to child
- Thought about ways to extract wish list logic from child
- Couldn't come up with a solution that does not require injecting the child with another class, which we didn't want because the child should only be a simple domain object and shouldn't depend on anything else.
- These thoughts lead us to think our whole project is a single big code smell. One point worrying us is that the child should probably not know about its gift granter, but unfortunately we didn't have enough time to come up with a better solution and so removed the states for the wish list again.

---

###### 19.01.2014

*00:14*: Added in Test Driver

*01:09*: Bits and bobs, added in log book entries using git history, cleaning up project.

*03:15*: Added UML diagrams and use cases.

##### *Going to bed*

*11:30-13:12*: Woke up and had idea how to solve yesterday's problem, so started working on a child proxy on new branch [child-proxy](https://github.com/irisSchaffer/SCCPMS/tree/child-proxy):

- *12:03*: Created tests for child proxy, child creator and wish list states and moved child related stuff to its own package

- *12:26*: Added ChildCreator, ChildProxy and WishListStates

- *13:12*: Put all new parts together and made tests work again, adapted test driver.


