using CleanThatCode.Community.Models.Dtos;
using CleanThatCode.Community.Models.Entities;
using CleanThatCode.Community.Repositories.Data;
using CleanThatCode.Community.Repositories.Implementations;
using FizzWare.NBuilder;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CleanThatCode.Community.Tests
{
    [TestClass]
    public class PostRepositoryTest
    {
        private Mock<ICleanThatCodeDbContext> _cleanMockDb;
        private PostRepository _postRepo;

        [TestInitialize]
        public void Initialize()
        {
            _cleanMockDb = new Mock<ICleanThatCodeDbContext>();
            _cleanMockDb.Setup(method => method.Posts).Returns(
                FizzWare.NBuilder.Builder<Post>
                .CreateListOfSize(3)
                .TheFirst(1).With(x => x.Title = "Grayskull").With(x => x.Author = "He-Man")
                .TheNext(1).With(x => x.Title = "Grayskull").With(x => x.Author = "He-Man")
                .TheLast(1).With(x => x.Title = "Hack the Planet!").With(x => x.Author = "Richard Stallman").Build());

            _postRepo = new PostRepository(_cleanMockDb.Object);
        }

        [TestMethod]
        public void GetAllPosts_NoFilter_ShouldContainAListOfThree()
        {
            var result = _postRepo.GetAllPosts("", "");
            Assert.AreEqual(3, result.Count());
        }

        [TestMethod]
        public void GetAllPosts_FilteredByTitle_ShouldContainAListOfTwo()
        {
            var result = _postRepo.GetAllPosts("Grayskull", "");
            Assert.AreEqual(2, result.Count());
        }

        [TestMethod]
        public void GetAllPosts_FilteredByAuthor_ShouldContainAListOfOne()
        {
            var result = _postRepo.GetAllPosts("", "Stallman");
            Assert.AreEqual(1, result.Count());
        }
    }
}
