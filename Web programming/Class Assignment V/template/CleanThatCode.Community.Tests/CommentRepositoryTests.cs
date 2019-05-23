using CleanThatCode.Community.Repositories.Implementations;
using CleanThatCode.Community.Tests.Mocks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CleanThatCode.Community.Tests
{
    [TestClass]
    public class CommentRepositoryTests
    {
        private CommentRepository _comRepo;
        private CleanThatCodeDbContextMock _mockDb;

        [TestInitialize]
        public void Initialize()
        {
            _mockDb = new CleanThatCodeDbContextMock();
            _comRepo = new CommentRepository(_mockDb);
        }

        [TestMethod]
        public void GetAllCommentsByPostId_GivenWrongPostId_ShouldReturnNoComments()
        {
            var result = _comRepo.GetAllCommentsByPostId(5);
            Assert.AreEqual(0, result.Count());
        }

        [TestMethod]
        public void GetAllCommentsByPostId_GivenValidPostId_ShouldReturnTwoComments()
        {
            var result = _comRepo.GetAllCommentsByPostId(1);
            Assert.AreEqual(2, result.Count());
        }

    }
}
