
class AVLNode(object):

    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.height = 1

class AVLTree(object):

    def insert(self, root, key):

        if not root:
           
            return AVLNode(key)
        elif key < root.key:
    
            root.left = self.insert(root.left, key)
        else:
            # adds key to the right if key is bigger than root
            root.right = self.insert(root.right, key)

        # finds height of larger subtree
        maxHeight = max(self.getHeight(root.left), self.getHeight(root.right))

        root.height = 1 + maxHeight

        balanceFactor = self.getBalance(root) # gets tree balance

        # balances if left subtree is larger than allowed
        if balanceFactor > 1:
            if key < root.left.key:
                return self.rightRotate(root)
            else:
                root.left = self.leftRotate(root.left)
                return self.rightRotate(root)

        # balances if left subtree is smaller than right
        if balanceFactor < -1:
            if key > root.right.key:
                return self.leftRotate(root)
            else:
                root.right = self.rightRotate(root.right)
                return self.leftRotate(root)

        return root

    def delete(self, root, key):

        if not root:
            return root
        elif key < root.key:
            root.left = self.delete(root.left, key) 
        elif key > root.key:
            root.right = self.delete(root.right, key) 
        else:
            
            if root.left is None:
                temp = root.right
                root = None
                return temp
            
            elif root.right is None:
                temp = root.left
                root = None
                return temp
            temp = self.getMaxValueNode(root.left)
            root.key = temp.key
            root.left = self.delete(root.left, temp.key)
        if root is None:
            return root

        maxHeight = max(self.getHeight(root.left), self.getHeight(root.right))
        root.height = 1 + maxHeight # updates root height

        balanceFactor = self.getBalance(root)

        # balances
        if balanceFactor > 1:
            if self.getBalance(root.left) >= 0:
                return self.rightRotate(root)
            else:
                root.left = self.leftRotate(root.left)
                return self.rightRotate(root)
        if balanceFactor < -1:
            if self.getBalance(root.right) <= 0:
                return self.leftRotate(root)
            else:
                root.right = self.rightRotate(root.right)
                return self.leftRotate(root)
        return root

    def leftRotate(self, z):

        y = z.right
        T2 = y.left
        y.left = z
        z.right = T2

        yMaxHeight = max(self.getHeight(y.left), self.getHeight(y.right))
        zMaxHeight = max(self.getHeight(z.left), self.getHeight(z.right))

        y.height = 1 + yMaxHeight
        z.height = 1 + zMaxHeight

        return y

    def rightRotate(self, z):
        y = z.left
        T3 = y.right
        y.right = z
        z.left = T3

        yMaxHeight = max(self.getHeight(y.left), self.getHeight(y.right))
        zMaxHeight = max(self.getHeight(z.left), self.getHeight(z.right))

        y.height = 1 + yMaxHeight
        z.height = 1 + zMaxHeight

        return y

    def getHeight(self, root):
        if not root:
            return 0
        return root.height

    def getBalance(self, root):
        if not root:
            return 0
        return self.getHeight(root.left) - self.getHeight(root.right)

    def getMaxValueNode(self, root):
        if root is None or root.right is None:
            return root
        return self.getMaxValueNode(root.right)