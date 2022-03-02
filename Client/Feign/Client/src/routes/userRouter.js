const express = require('express');
const usersController = require('../controllers/usersController');

const router = express.Router();

router.get('/', usersController.getUsers);
router.get('/:userId',  usersController.getUser);
router.patch('/:userId', usersController.patchUser);
router.post('/', usersController.addUser);
router.put('/:userId',usersController.putUser);
router.delete('/:userId',  usersController.deleteUser);

module.exports = router;
